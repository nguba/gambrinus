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
package me.nguba.gambrinus.command;

import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.process.Temperature;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ChangeSetpoint implements VesselCommand
{
    public static ChangeSetpoint on(final VesselId vesselId, final Temperature setpoint)
    {
        return new ChangeSetpoint(vesselId, setpoint);
    }

    private final Temperature setpoint;

    private final VesselId vesselId;

    private ChangeSetpoint(final VesselId vesselId, final Temperature setpoint)
    {
        this.vesselId = vesselId;
        this.setpoint = setpoint;
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
        final ChangeSetpoint other = (ChangeSetpoint) obj;
        if (setpoint == null) {
            if (other.setpoint != null)
                return false;
        } else if (!setpoint.equals(other.setpoint))
            return false;
        if (vesselId == null) {
            if (other.vesselId != null)
                return false;
        } else if (!vesselId.equals(other.vesselId))
            return false;
        return true;
    }

    @Override
    public VesselId getId()
    {
        return vesselId;
    }

    public Temperature getSetpoint()
    {
        return setpoint;
    }

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = prime * result + (setpoint == null ? 0 : setpoint.hashCode());
        result = prime * result + (vesselId == null ? 0 : vesselId.hashCode());
        return result;
    }
}
