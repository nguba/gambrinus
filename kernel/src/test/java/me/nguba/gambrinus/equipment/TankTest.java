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

import me.nguba.gambrinus.process.ProcessValue;
import me.nguba.gambrinus.process.Temperature;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class TankTest
{
    private final ProbeId hotWaterInlet = ProbeId.of("Hot Water Inlet");

    final Tank tank = Tank.with(TankId.of("Mash Tun"));

    private void addProbe(final ProbeId probeId)
    {
        tank.add(probeId, () -> ProcessValue.with(Temperature.celsius(64.0)));
    }

    @Test
    void readNonExistingProbe() throws Exception
    {
        assertThrows(ProbeNotConfigured.class, () -> tank.read(hotWaterInlet));

    }

    @Test
    void readProbe() throws Exception
    {
        addProbe(hotWaterInlet);

        final ProcessValue pv = tank.read(hotWaterInlet);

        assertEquals(pv, ProcessValue.with(Temperature.celsius(64.0)));
    }
}
