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
package me.nguba.gambrinus.command.vessel.create;

import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.event.MutatorEvent;

import java.time.Instant;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class VesselCreated extends MutatorEvent
{
    public static VesselCreated from(final VesselId id)
    {
        return new VesselCreated(id);
    }

    private final VesselId vesselId;

    private VesselCreated(final VesselId vesselId)
    {
        super(Instant.now());
        this.vesselId = vesselId;
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
        final VesselCreated other = (VesselCreated) obj;
        if (vesselId == null) {
            if (other.vesselId != null)
                return false;
        } else if (!vesselId.equals(other.vesselId))
            return false;
        return true;
    }

    public VesselId getVesselId()
    {
        return vesselId;
    }

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = prime * result + (vesselId == null ? 0 : vesselId.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("VesselCreated [vesselId=").append(vesselId).append("]");
        return builder.toString();
    }
}
