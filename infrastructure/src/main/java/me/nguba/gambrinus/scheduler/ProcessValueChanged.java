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

package me.nguba.gambrinus.scheduler;

import me.nguba.gambrinus.event.DomainEvent;
import me.nguba.gambrinus.process.ProcessValue;
import me.nguba.gambrinus.process.Segment;
import me.nguba.gambrinus.scheduler.state.State;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ProcessValueChanged extends DomainEvent
{
    public static ProcessValueChanged on(final State state,
                                         final Segment segment,
                                         final ProcessValue processValue)
    {
        return new ProcessValueChanged(state, segment, processValue);
    }

    private final ProcessValue processValue;
    private final Segment      segment;
    private final State        state;

    private ProcessValueChanged(final State state,
                                final Segment unit,
                                final ProcessValue processValue)
    {
        this.state = state;
        segment = unit;
        this.processValue = processValue;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ProcessValueChanged other = (ProcessValueChanged) obj;
        if (processValue == null) {
            if (other.processValue != null)
                return false;
        } else if (!processValue.equals(other.processValue))
            return false;
        if (segment == null) {
            if (other.segment != null)
                return false;
        } else if (!segment.equals(other.segment))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        return true;
    }

    public ProcessValue getProcessValue()
    {
        return processValue;
    }

    public Segment getSegment()
    {
        return segment;
    }

    public State getState()
    {
        return state;
    }

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = super.hashCode();
        result = prime * result + (processValue == null ? 0 : processValue.hashCode());
        result = prime * result + (segment == null ? 0 : segment.hashCode());
        result = prime * result + (state == null ? 0 : state.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("ProcessValueChanged [");
        if (state != null)
            builder.append("state=").append(state).append(", ");
        if (processValue != null)
            builder.append("processValue=").append(processValue).append(", ");
        if (segment != null)
            builder.append("segment=").append(segment);
        builder.append("]");
        return builder.toString();
    }
}
