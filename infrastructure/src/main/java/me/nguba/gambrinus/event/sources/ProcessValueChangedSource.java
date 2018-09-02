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

import me.nguba.gambrinus.command.temperature.process.ProcessValueChanged;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ProcessValueChangedSource extends EventSource
{
    public static ProcessValueChangedSource from(final ProcessValueChanged event)
    {
        final ProcessValueChangedSource source = new ProcessValueChangedSource();
        source.vesselId = event.getVesselId().getValue();
        source.processValue = TemperatureField.from(event.getProcessValue());
        source.timestamp = event.getTimestamp();
        return source;
    }

    private TemperatureField processValue;

    private String vesselId;

    ProcessValueChangedSource()
    {
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
        final ProcessValueChangedSource other = (ProcessValueChangedSource) obj;
        if (processValue == null) {
            if (other.processValue != null)
                return false;
        } else if (!processValue.equals(other.processValue))
            return false;
        if (vesselId == null) {
            if (other.vesselId != null)
                return false;
        } else if (!vesselId.equals(other.vesselId))
            return false;
        return true;
    }

    public TemperatureField getProcessValue()
    {
        return processValue;
    }

    public String getVesselId()
    {
        return vesselId;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (processValue == null ? 0 : processValue.hashCode());
        result = prime * result + (vesselId == null ? 0 : vesselId.hashCode());
        return result;
    }

    public void setProcessValue(final TemperatureField processValue)
    {
        this.processValue = processValue;
    }

    public void setVesselId(final String vesselId)
    {
        this.vesselId = vesselId;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("ProcessValueChangedSource [vesselId=").append(vesselId)
                .append(", processValue=").append(processValue).append(", timestamp=")
                .append(timestamp).append("]");
        return builder.toString();
    }
}
