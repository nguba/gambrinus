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

import java.time.Instant;

import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.process.Temperature;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class SetpointChanged extends VesselMutatorEvent
{
    public static SetpointChanged on(final VesselId vesselId, final Temperature setpoint)
    {
        return new SetpointChanged(vesselId, setpoint);
    }

    private final Temperature setpoint;

    private SetpointChanged(final VesselId vesselId, final Temperature setpoint)
    {
        super(Instant.now().toEpochMilli(), vesselId);
        this.setpoint = setpoint;
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
        final SetpointChanged other = (SetpointChanged) obj;
        if (setpoint == null) {
            if (other.setpoint != null)
                return false;
        } else if (!setpoint.equals(other.setpoint))
            return false;
        return true;
    }

    public Temperature getSetpoint()
    {
        return setpoint;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (setpoint == null ? 0 : setpoint.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("SetpointChanged [setpoint=").append(setpoint).append(", vesselId=")
                .append(vesselId).append(", timestamp=").append(timestamp).append("]");
        return builder.toString();
    }
}
