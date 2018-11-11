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

package me.nguba.gambrinus.scheduler.event;

import me.nguba.gambrinus.event.DomainEvent;
import me.nguba.gambrinus.scheduler.state.State;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class SegmentStateChanged extends DomainEvent
{
    public static SegmentStateChanged on(final State state)
    {
        return new SegmentStateChanged(state);
    }

    private final State state;

    private SegmentStateChanged(final State state)
    {
        this.state = state;
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
        final SegmentStateChanged other = (SegmentStateChanged) obj;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        return true;
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
        result = prime * result + (state == null ? 0 : state.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("SegmentStateChanged [");
        if (state != null)
            builder.append("state=").append(state).append(", ");
        builder.append("timestamp=").append(timestamp).append("]");
        return builder.toString();
    }

}
