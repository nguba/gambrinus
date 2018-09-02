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

import me.nguba.gambrinus.process.Temperature;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class OwfsSensorTest
{
    private OwfsSensor sensor;

    @Test
    void mountFailure() throws Exception
    {
        assertThat(OwfsSensor.from(OwfsRoot.of("foo"), OwfsMother.address()).isValid()).isFalse();
    }

    @Test
    void read() throws Exception
    {
        final Temperature t1 = sensor.read().get();
        final Temperature expected = Temperature.celsius(68.5);

        assertThat(t1).isEqualTo(expected);
    }

    @Test
    void readFromEmptyTemperatureFile() throws Exception
    {
        final OwfsSensor empty = OwfsSensor.from(OwfsRoot.of("src/test/resources/emptyfs"),
                                                 OwfsMother.address());

        final Optional<Temperature> temperature = empty.read();

        assertThat(temperature.isPresent()).isFalse();
    }

    @Test
    void readMultiple() throws Exception
    {
        final Temperature t1 = sensor.read().get();
        final Temperature t2 = sensor.read().get();

        assertThat(t1).isEqualTo(t2);
    }

    @BeforeEach
    void setUp() throws Exception
    {
        sensor = OwfsSensor.from(OwfsMother.root(), OwfsMother.address());
    }
}
