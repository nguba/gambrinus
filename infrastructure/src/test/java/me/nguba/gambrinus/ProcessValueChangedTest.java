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

import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.owfs.ProcessValueChanged;
import me.nguba.gambrinus.process.Temperature;
import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class ProcessValueChangedTest
{
    private final Temperature expected = Temperature.celsius(29);

    private final OneWireAddress address = OneWireAddress.of("28.273B5D070000");

    @Test
    void failsWhenNoVesselGiven()
    {
        assertThrows(IllegalArgumentException.class,
                     () -> ProcessValueChanged.on(null, expected));
    }

    @Test
    void processValue()
    {
        assertThat(ProcessValueChanged.on(address, expected).getProcessValue())
                .isEqualTo(expected);
    }

    @Test
    void processValueNull()
    {
        assertThat(ProcessValueChanged.on(address, null).getProcessValue())
                .isEqualTo(Temperature.celsius(0));
    }

    @Test
    void vesselId()
    {
        assertThat(ProcessValueChanged.on(address, expected).getAddress()).isEqualTo(address);
    }

    @Test
    void equalityContract()
    {
        EqualsVerifier.forClass(ProcessValueChanged.class).usingGetClass().verify();
    }
}
