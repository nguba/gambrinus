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
package me.nguba.gambrinus.command.temperature.setpoint;

import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.event.MutatorEvent;

import java.time.Instant;

public abstract class VesselMutatorEvent extends MutatorEvent
{
    protected final VesselId vesselId;

    public VesselMutatorEvent(final Instant now, final VesselId vesselId)
    {
        super(now);
        this.vesselId = vesselId;
    }

    public VesselMutatorEvent(final long timestamp, final VesselId vesselId)
    {
        super(timestamp);
        this.vesselId = vesselId;
    }

    public VesselMutatorEvent(final String timestamp, final VesselId vesselId)
    {
        super(timestamp);
        this.vesselId = vesselId;
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
        final VesselMutatorEvent other = (VesselMutatorEvent) obj;
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
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (vesselId == null ? 0 : vesselId.hashCode());
        return result;
    }

}
