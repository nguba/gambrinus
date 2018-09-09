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
package me.nguba.gambrinus;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.onewire.OneWireAddress;

class AdministratorTest
{
    private Administrator admin;

    private final VesselRepository vessels = new VesselRepository();

    @Test
    void findAddresses() throws Exception
    {
        final OneWireAddress a = OneWireAddress.of("28.273B5D070000");
        final OneWireAddress b = OneWireAddress.of("28.4BBB68080000");

        assertThat(admin.findAddresses("src/test/resources/owfs")).containsOnly(a, b);
    }

    @Test
    void findVessels() throws Exception
    {
        final Vessel[] expected = { Vessel.inactive(VesselId.of("a")),
                Vessel.inactive(VesselId.of("b")) };
        for (final Vessel v : expected)
            vessels.create(v);

        assertThat(admin.findVessels()).containsOnly(expected);
    }

    @BeforeEach
    void setUp()
    {
        admin = new Administrator(vessels);
    }
}
