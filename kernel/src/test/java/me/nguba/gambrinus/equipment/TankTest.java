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

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class TankTest
{
    final ProbeId hotWaterInlet = ProbeId.from("Hot Water Inlet");

    final Tank tank = Tank.with(TankId.from("Mash Tun"));

    @Test
    void addCooler()
    {
        withCooler();

        assertThat(tank.cooler()).isPresent();
    }

    @Test
    void addHeater()
    {
        withHeater();

        assertThat(tank.heater()).isPresent();
    }

    private void addProbe(final ProbeId probeId)
    {
        tank.add(probeId, () -> ProcessValue.with(Temperature.celsius(64.0)));
    }

    @Test
    void coolerOnOff() throws Exception
    {
        withCooler();

        assertThat(getCooler().currentState()).isEqualTo(Switched.State.OFF);

        tank.coolerOn();

        assertThat(getCooler().currentState()).isEqualTo(Switched.State.ON);

        tank.coolerOff();

        assertThat(getCooler().currentState()).isEqualTo(Switched.State.OFF);
    }

    private HeatExchanger getCooler()
    {
        return tank.cooler().get();
    }

    private HeatExchanger getHeater()
    {
        return tank.heater().get();
    }

    @Test
    void switchOffUnconfiguredHeater()
    {
        Exception exception = assertThrows(HeatExchangerNotAvailable.class, () -> tank.heaterOff());
        
        assertThat(exception).hasMessage("No heat exchanger available for: heating");
    }
    
    @Test
    void switchOffUnconfiguredCooler()
    {
        Exception exception = assertThrows(HeatExchangerNotAvailable.class, () -> tank.coolerOff());
        
        assertThat(exception).hasMessage("No heat exchanger available for: cooling");
    }
    
    @Test
    void switchOnUnconfiguredCooler()
    {
        Exception exception = assertThrows(HeatExchangerNotAvailable.class, () -> tank.coolerOn());
        
        assertThat(exception).hasMessage("No heat exchanger available for: cooling");
    }
    
    @Test
    void switchOnUnconfiguredHeater()
    {
        Exception exception = assertThrows(HeatExchangerNotAvailable.class, () -> tank.heaterOn());
        
        assertThat(exception).hasMessage("No heat exchanger available for: heating");
    }

    @Test
    void heaterOnOff() throws Exception
    {
        withHeater();

        assertThat(getHeater().currentState()).isEqualTo(Switched.State.OFF);

        tank.heaterOn();

        assertThat(getHeater().currentState()).isEqualTo(Switched.State.ON);

        tank.heaterOff();

        assertThat(getHeater().currentState()).isEqualTo(Switched.State.OFF);
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

    private void withCooler()
    {
        tank.addCooler(HeatExchangerMock.instance());
    }

    private void withHeater()
    {
        tank.addHeater(HeatExchangerMock.instance());
    }
}
