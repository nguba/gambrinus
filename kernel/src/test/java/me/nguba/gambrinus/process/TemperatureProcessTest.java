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

package me.nguba.gambrinus.process;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

// test how much time is remaining
// ensure it can resume from where it left off
// we need to know when all the items have been processed
//
/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class TemperatureProcessTest
{
    final TemperatureProcess process = TemperatureProcess.create();

    @Test
    void scheduleMultipleUnitsDisplayedInOrder()
    {
        final List<TemperatureUnit> expected = new LinkedList<>();
        expected.add(ProcessMother.firstUnit());
        expected.add(ProcessMother.secondUnit());
        expected.add(ProcessMother.thirdUnit());

        for (final TemperatureUnit unit : expected)
            process.schedule(unit);

        final List<TemperatureUnit> actual = new LinkedList<>();
        for (int i = 0; i < expected.size(); i++)
            actual.add(process.nextUnit());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void scheduleSingleTemperatureUnit()
    {
        process.schedule(ProcessMother.firstUnit());

        final TemperatureUnit unit = process.currentUnit();

        assertThat(unit).isEqualTo(ProcessMother.firstUnit());
    }

}
