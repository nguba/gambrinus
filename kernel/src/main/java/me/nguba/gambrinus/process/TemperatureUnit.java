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

import me.nguba.gambrinus.ddd.Entity;

import java.time.Duration;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class TemperatureUnit extends Entity<TemperatureUnitId>
{
    public static TemperatureUnit with(final TemperatureUnitId id,
                                       final Duration duration,
                                       final Setpoint setpoint)
    {
        return new TemperatureUnit(id, duration, setpoint, Duration.ZERO);
    }

    private final Duration consumed;

    private final Duration duration;

    private final Setpoint setpoint;

    private TemperatureUnit(final TemperatureUnitId id,
                            final Duration duration,
                            final Setpoint setpoint,
                            final Duration consumed)
    {
        super(id);
        this.duration = duration;
        this.setpoint = setpoint;
        this.consumed = consumed;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("TemperatureUnit [duration=").append(duration).append(", consumed=")
                .append(consumed).append(", setpoint=").append(setpoint).append("]");
        return builder.toString();
    }

}
