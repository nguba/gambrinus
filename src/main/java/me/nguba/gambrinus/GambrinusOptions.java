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
package me.nguba.gambrinus;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
@ConfigurationProperties(prefix = "gambrinus")
public class GambrinusOptions
{
    private String mountpoint;

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final GambrinusOptions other = (GambrinusOptions) obj;
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
        final int prime  = 31;
        int       result = 1;
        result = prime * result + (mountpoint == null ? 0 : mountpoint.hashCode());
        return result;
    }

    public void setMountpoint(final String mountpoint)
    {
        this.mountpoint = mountpoint;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("GambrinusOptions [mountpoint=").append(mountpoint).append("]");
        return builder.toString();
    }

}
