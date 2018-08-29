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
package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.process.Temperature;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class OwfsMonitorTest
{
    private OwfsMonitor monitor;

    private final OwfsRoot root = OwfsRoot.of("src/test/resources/owfs");

    private final VesselRepository vessels = new VesselRepository();

    private final VesselId vesselId = VesselId.of("mash");

    @BeforeEach
    void setUp()
    {
        monitor = new OwfsMonitor(vessels);
    }

    @Test
    void assignSensorToVessel()
    {
        monitor.assign(OneWireAddress.of("28.273B5D070000"), vesselId);

        assertThat(monitor.vesselFor(OneWireAddress.of("28.273B5D070000"))).isEqualTo(vesselId);
    }

    @Test
    void updatesTemperatureOnTargetVessel()
    {
        vessels.create(Vessel.inactive(vesselId));

        assertThat(readProcessValue()).isEqualTo(Temperature.celsius(0));

        monitor.assign(OneWireAddress.of("28.273B5D070000"), vesselId);
        monitor.read(root);

        assertThat(readProcessValue()).isEqualTo(Temperature.celsius(55.5));
    }

    private Temperature readProcessValue()
    {
        return vessels.read(vesselId).get().processValue();
    }

}
