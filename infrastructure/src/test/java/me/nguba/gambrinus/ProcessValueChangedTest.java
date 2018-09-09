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
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.event.ProcessValueChanged;
import me.nguba.gambrinus.process.Temperature;
import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class ProcessValueChangedTest
{
    private final Temperature expected = Temperature.celsius(29);

    private final VesselId vesselId = VesselId.of("Mash Tun");

    @Test
    void equalityContract()
    {
        EqualsVerifier.forClass(ProcessValueChanged.class).usingGetClass().verify();
    }

    @Test
    void failsWhenNoVesselGiven()
    {
        assertThrows(IllegalArgumentException.class,
                     () -> ProcessValueChanged.on(null, expected));
    }

    @Test
    void processValue()
    {
        assertThat(ProcessValueChanged.on(vesselId, expected).getProcessValue())
                .isEqualTo(expected);
    }

    @Test
    void processValueNull()
    {
        assertThat(ProcessValueChanged.on(vesselId, null).getProcessValue())
                .isEqualTo(Temperature.celsius(0));
    }

    @Test
    void toStringWorks()
    {
        assertThat(ProcessValueChanged.on(vesselId, expected).toString()).isNotBlank();
    }

    @Test
    void vesselId()
    {
        assertThat(ProcessValueChanged.on(vesselId, expected).getVesselId()).isEqualTo(vesselId);
    }
}
