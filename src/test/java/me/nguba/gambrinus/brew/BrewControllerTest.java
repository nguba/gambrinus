/*
    Copyright (C) 2018  Nicolai P. Guba

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package me.nguba.gambrinus.brew;

import me.nguba.gambrinus.ApplicationMother;
import me.nguba.gambrinus.GambrinusControllerTest;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.process.Temperature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 *
 */
@GambrinusControllerTest
class BrewControllerTest
{
    @Autowired
    private MockMvc mvc;

    @Autowired
    private VesselRepository vessels;

    @AfterEach
    void tearDown()
    {
        for (final Vessel v : vessels.findAll()) {
            vessels.delete(v.getId());
        }
    }

    @Test
    @DisplayName("throws error when vessel not found")
    void changeSetpoint_NoVesselFound() throws Exception
    {
        mvc.perform(put("/api/brew/heat/{name}/{temperature}", "mash", 55.5))
                .andDo(print()).andExpect(status().isConflict());
    }

    @Test
    @DisplayName("throws error when no sensor assigned to vessel")
    void changeSetpoint_Inactive() throws Exception
    {
        vessels.create(Vessel.inactive(VesselId.of("mash")));

        mvc.perform(put("/api/brew/heat/{name}/{temperature}", "mash", 55.5))
                .andDo(print()).andExpect(status().isConflict());
    }

    @Test
    @DisplayName("mutates the setpoint on the vessel")
    void changeSetpoint() throws Exception
    {
        final Vessel mashTun = createVessel();

        assertThat(vessels.read(mashTun.getId()).get().setpoint())
                .isEqualTo(Temperature.celsius(0));

        mvc.perform(put("/api/brew/heat/{name}/{temperature}", mashTun.getId(), 55.5))
                .andDo(print()).andExpect(status().isOk());

        assertThat(vessels.read(mashTun.getId()).get().setpoint())
                .isEqualTo(Temperature.celsius(55.5));
    }

    private Vessel createVessel()
    {
        final Vessel mashTun = ApplicationMother.mashTun();
        vessels.create(mashTun);
        return mashTun;
    }

    @Test
    @DisplayName("reads process value from vessel")
    void readTemperature() throws Exception
    {
        final Vessel mashTun = createVessel();
        mashTun.processValue(Temperature.celsius(68.3));

        mvc.perform(get("/api/brew/temperature/{name}", mashTun.getId()))
                .andDo(print()).andExpect(status().isOk());
    }
}
