package me.nguba.gambrinus.process.setpoint;

import me.nguba.gambrinus.cqrs.command.CommandEvent;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.process.Temperature;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class SetpointChanged implements CommandEvent
{
    private final VesselId vesselId;

    private final Temperature setpoint;

    private SetpointChanged(final VesselId vesselId, final Temperature setpoint)
    {
        this.vesselId = vesselId;
        this.setpoint = setpoint;
    }

    public static SetpointChanged on(final VesselId vesselId, final Temperature setpoint)
    {
        return new SetpointChanged(vesselId, setpoint);
    }

    public VesselId getVesselId()
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
        final int prime = 31;
        int result = 1;
        result = prime * result + ((setpoint == null) ? 0 : setpoint.hashCode());
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
        final SetpointChanged other = (SetpointChanged) obj;
        if (setpoint == null) {
            if (other.setpoint != null) {
                return false;
            }
        } else if (!setpoint.equals(other.setpoint)) {
            return false;
        }
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
        builder.append("SetpointChanged [vesselId=").append(vesselId).append(", setpoint=")
                .append(setpoint).append("]");
        return builder.toString();
    }
}
