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

package me.nguba.gambrinus.scheduler.state;

import me.nguba.gambrinus.process.Segment;
import me.nguba.gambrinus.process.Setpoint;
import me.nguba.gambrinus.process.Temperature;
import me.nguba.gambrinus.process.TemperatureUnitId;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public enum ProcessMother
{
    ;

    /**
     * setpoint is at 50.0 Celsius
     *
     * @return
     */
    public static Segment firstUnit()
    {
        return Segment.with(TemperatureUnitId.from("unit 1"),
                            Duration.ofSeconds(2),
                            Setpoint.from(Temperature.celsius(50.0)));
    }

    static List<Segment> scheduledUnits()
    {
        final List<Segment> expected = new LinkedList<>();
        expected.add(firstUnit());
        expected.add(secondUnit());
        expected.add(thirdUnit());
        return expected;
    }

    /**
     * setpoint is at 60.0 Celsius
     *
     * @return
     */
    public static Segment secondUnit()
    {
        return Segment.with(TemperatureUnitId.from("unit 2"),
                            Duration.ofSeconds(2),
                            Setpoint.from(Temperature.celsius(60.0)));
    }

    /**
     * setpoint is at 70.0 Celsius
     *
     * @return
     */
    public static Segment thirdUnit()
    {
        return Segment.with(TemperatureUnitId.from("unit 3"),
                            Duration.ofSeconds(2),
                            Setpoint.from(Temperature.celsius(70.0)));
    }

}
