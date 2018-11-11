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
import me.nguba.gambrinus.process.Segment;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public abstract class SegmentEvent extends DomainEvent
{
    protected Segment segment;

    protected SegmentEvent(final Segment segment)
    {
        this.segment = segment;
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
        final SegmentEvent other = (SegmentEvent) obj;
        if (segment == null) {
            if (other.segment != null)
                return false;
        } else if (!segment.equals(other.segment))
            return false;
        return true;
    }

    public Segment getSegment()
    {
        return segment;
    }

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = super.hashCode();
        result = prime * result + (segment == null ? 0 : segment.hashCode());
        return result;
    }

}
