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
package me.nguba.gambrinus.admin;

import me.nguba.gambrinus.ApplicationMother;
import me.nguba.gambrinus.GambrinusControllerTest;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
@GambrinusControllerTest
class AdminControllerTest
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
    void emptyVessels() throws Exception
    {
        final MvcResult result = mvc.perform(get("/api/admin/vessel")).andDo(print())
                .andExpect(status().isOk()).andReturn();

        assertEquals("[ ]", result.getResponse().getContentAsString());
    }

    @Test
    void getVessels() throws Exception
    {
        final Vessel[] expected = { Vessel.inactive(VesselId.of("a")),
                Vessel.inactive(VesselId.of("b")) };
        for (final Vessel v : expected) {
            vessels.create(v);
        }

        mvc.perform(get("/api/admin/vessel")).andDo(print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void getVesselNotFound() throws Exception
    {
        mvc.perform(get("/api/admin/vessel/{name}", "mash")).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getVessel() throws Exception
    {
        final Vessel vessel = ApplicationMother.mashTun();

        vessels.create(vessel);

        mvc.perform(get("/api/admin/vessel/{name}", "mash")).andDo(print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void getSensors() throws Exception
    {
        mvc.perform(get("/api/admin/sensor")).andDo(print())
                .andExpect(status().isOk()).andReturn();

    }

    @Test
    void createVessel() throws Exception
    {
        mvc.perform(post("/api/admin/vessel/{name}/{address}", "mash", "28.273B5D070000"))
                .andDo(print())
                .andExpect(status().isCreated()).andReturn();
    }
}
