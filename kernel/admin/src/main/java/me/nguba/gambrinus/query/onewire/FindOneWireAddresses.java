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
package me.nguba.gambrinus.query.onewire;

import me.nguba.gambrinus.cqrs.query.Query;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class FindOneWireAddresses implements Query
{
    public static FindOneWireAddresses on(final String mountpoint)
    {
        return new FindOneWireAddresses(mountpoint);
    }

    private final String mountpoint;

    private FindOneWireAddresses(final String mountpoint)
    {
        this.mountpoint = mountpoint;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final FindOneWireAddresses other = (FindOneWireAddresses) obj;
        if (mountpoint == null) {
            if (other.mountpoint != null)
                return false;
        } else if (!mountpoint.equals(other.mountpoint))
            return false;
        return true;
    }

    public String getMountpoint()
    {
        return mountpoint;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (mountpoint == null ? 0 : mountpoint.hashCode());
        return result;
    }
}
