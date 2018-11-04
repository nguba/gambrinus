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

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ProcessValueChanged extends DomainEvent
{
    public static ProcessValueChanged on(final ProcessValue pv)
    {
        return new ProcessValueChanged(pv);
    }

    private final ProcessValue pv;

    private ProcessValueChanged(final ProcessValue pv)
    {
        this.pv = pv;
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
        if (pv == null) {
            if (other.pv != null)
                return false;
        } else if (!pv.equals(other.pv))
            return false;
        return true;
    }

    public ProcessValue getPv()
    {
        return pv;
    }

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = super.hashCode();
        result = prime * result + (pv == null ? 0 : pv.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("ProcessValueChanged [");
        if (pv != null)
            builder.append("pv=").append(pv).append(", ");
        builder.append("timestamp=").append(timestamp).append("]");
        return builder.toString();
    }
}
