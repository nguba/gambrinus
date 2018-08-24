package me.nguba.gambrinus.command.vessel.create;

import java.time.Instant;

import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.event.MutatorEvent;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class VesselCreated extends MutatorEvent
{
    private final VesselId vesselId;

    private VesselCreated(final VesselId vesselId)
    {
    	super(Instant.now());
        this.vesselId = vesselId;
    }

    public static VesselCreated from(final VesselId id)
    {
        return new VesselCreated(id);
    }

    public VesselId getVesselId()
    {
        return vesselId;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vesselId == null) ? 0 : vesselId.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VesselCreated other = (VesselCreated) obj;
        if (vesselId == null) {
            if (other.vesselId != null) {
                return false;
            }
        } else if (!vesselId.equals(other.vesselId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("VesselCreated [vesselId=").append(vesselId).append("]");
        return builder.toString();
    }
}
