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
package me.nguba.gambrinus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.process.Temperature;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class ChangeSetpointTest
{
    private final ChangeSetpoint command = ChangeSetpoint.on(VesselId.of("setpoint test"),
                                                             Temperature.celsius(58.0));

    @Test
    void hasSetpoint()
    {
        assertEquals(command.getSetpoint(), Temperature.celsius(58.0));
    }

    @Test
    void hasVesselId()
    {
        assertEquals(command.getId(), VesselId.of("setpoint test"));
    }
}
