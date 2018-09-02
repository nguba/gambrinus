package me.nguba.gambrinus.command.temperature.setpoint;

import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.event.MutatorEvent;

import java.time.Instant;

public abstract class VesselMutatorEvent extends MutatorEvent
{
    protected final VesselId vesselId;

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

    public VesselMutatorEvent(final Instant now, final VesselId vesselId)
    {
        super(now);
        this.vesselId = vesselId;
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
        result = prime * result + ((vesselId == null) ? 0 : vesselId.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VesselMutatorEvent other = (VesselMutatorEvent) obj;
        if (vesselId == null) {
            if (other.vesselId != null) {
                return false;
            }
        } else if (!vesselId.equals(other.vesselId)) {
            return false;
        }
        return true;
    }

}
