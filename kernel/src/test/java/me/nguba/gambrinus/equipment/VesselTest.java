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
package me.nguba.gambrinus.equipment;

import me.nguba.gambrinus.process.Temperature;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VesselTest
{
    private final Vessel vessel = Vessel.inactive(VesselId.of("junit"));

    @Test
    @DisplayName("Temperature should be 0 Celsius by default")
    public void TemperatureIsZeroByDefault()
    {
        assertThat(vessel.setpoint()).isEqualTo(Temperature.celsius(0));
    }

    @Test
    @DisplayName("Temperature is mutable")
    public void canChangeTemperature()
    {
        vessel.setpoint(Temperature.celsius(65.5));

        assertThat(vessel.setpoint()).isEqualTo(Temperature.celsius(65.5));
    }
}
