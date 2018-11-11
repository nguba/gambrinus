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

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class TemperatureProcessTest
{
    final TemperatureProcess process = TemperatureProcess.empty();

    @Test
    void iterateThroughUnits()
    {
        final List<Segment> expected = ProcessMother.scheduledUnits();

        scheduleAll(expected);

        final List<Segment> actual = new LinkedList<>();
        for (final Segment unit : process)
            actual.add(unit);

        assertThat(actual).isEqualTo(expected);
    }

    private void scheduleAll(final List<Segment> expected)
    {
        for (final Segment unit : expected)
            process.schedule(unit);
    }

    @Test
    void scheduleSingleTemperatureUnit()
    {
        process.schedule(ProcessMother.firstUnit());

        final Segment unit = process.current();

        assertThat(unit).isEqualTo(ProcessMother.firstUnit());
    }

}
