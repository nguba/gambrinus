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

package me.nguba.gambrinus.event.sources;

import me.nguba.gambrinus.command.temperature.setpoint.SetpointChanged;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class SetpointChangedSource extends EventSource
{
    public static EventSource from(final SetpointChanged event)
    {
        final SetpointChangedSource source = new SetpointChangedSource();
        source.setpoint = TemperatureField.from(event.getSetpoint());
        source.vesselName = event.getVesselId().toString();
        source.timestamp = event.getTimestamp();
        return source;
    }

    private TemperatureField setpoint;

    private String vesselName;

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final SetpointChangedSource other = (SetpointChangedSource) obj;
        if (setpoint == null) {
            if (other.setpoint != null)
                return false;
        } else if (!setpoint.equals(other.setpoint))
            return false;
        if (vesselName == null) {
            if (other.vesselName != null)
                return false;
        } else if (!vesselName.equals(other.vesselName))
            return false;
        return true;
    }

    public TemperatureField getSetpoint()
    {
        return setpoint;
    }

    public String getVesselName()
    {
        return vesselName;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (setpoint == null ? 0 : setpoint.hashCode());
        result = prime * result + (vesselName == null ? 0 : vesselName.hashCode());
        return result;
    }

    public void setSetpoint(final TemperatureField setpoint)
    {
        this.setpoint = setpoint;
    }

    public void setVesselName(final String vesselName)
    {
        this.vesselName = vesselName;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("SetpointChangedSource [setpoint=").append(setpoint).append(", vesselName=")
                .append(vesselName).append(", timestamp=").append(timestamp).append("]");
        return builder.toString();
    }
}
