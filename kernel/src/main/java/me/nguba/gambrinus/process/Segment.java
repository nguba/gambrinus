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
import java.time.Instant;

/**
 * Segment within a program for heating a vessel.
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Segment extends Entity<TemperatureUnitId>
{
    public static Segment with(final TemperatureUnitId id,
                               final Duration duration,
                               final Setpoint setpoint)
    {
        return new Segment(id, duration, setpoint, Duration.ZERO);
    }

    private final Duration duration;

    private Instant end;

    private Duration remaining;

    private final Setpoint setpoint;

    private Instant start;

    private Segment(final TemperatureUnitId id,
                    final Duration duration,
                    final Setpoint setpoint,
                    final Duration remaining)
    {
        super(id);
        this.duration = duration;
        this.setpoint = setpoint;
        this.remaining = remaining;
    }

    public void expire()
    {
        end = Instant.now();
    }

    public boolean hasSetpointReached(final ProcessValue processValue)
    {
        if (setpoint != null && processValue != null)
            return processValue.getValue().getValue() >= setpoint.getValue().getValue();

        return true;
    }

    public boolean isComplete()
    {
        if (end == null)
            startTimer();

        remaining = Duration.between(Instant.now(), end);
        return remaining.isNegative() || remaining.isZero();
    }

    public Setpoint setpoint()
    {
        return setpoint;
    }

    public void startTimer()
    {
        start = Instant.now();
        end = start.plus(duration);
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("Segment [");
        if (setpoint != null)
            builder.append("setpoint=").append(setpoint).append(", ");
        if (remaining != null)
            builder.append("remaining=").append(remaining).append(", ");
        if (duration != null)
            builder.append("duration=").append(duration).append(", ");
        if (start != null)
            builder.append("start=").append(start).append(", ");
        if (end != null)
            builder.append("end=").append(end).append(", ");
        if (getId() != null)
            builder.append("getId()=").append(getId());
        builder.append("]");
        return builder.toString();
    }
}
