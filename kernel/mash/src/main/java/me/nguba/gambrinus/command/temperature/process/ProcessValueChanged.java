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
package me.nguba.gambrinus.command.temperature.process;

import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.event.MutatorEvent;
import me.nguba.gambrinus.process.Temperature;

import java.time.Instant;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ProcessValueChanged extends MutatorEvent
{
    public static ProcessValueChanged on(final VesselId address, final Temperature expected)
    {
        return ProcessValueChanged.from(Instant.now(), address, expected);
    }

    public static ProcessValueChanged from(final Instant instant,
                                           final VesselId vesselId,
                                           final Temperature expected)
    {
        if (vesselId == null) {
            throw new IllegalArgumentException("OneWireAddres cannot be null.");
        }

        return new ProcessValueChanged(instant,
                                       vesselId,
                                       expected == null ? Temperature.celsius(0) : expected);
    }

    protected final Temperature processValue;

    protected final VesselId vesselId;

    private ProcessValueChanged(final Instant instant,
                                final VesselId vesselId,
                                final Temperature processValue)
    {
        super(instant);
        this.vesselId = vesselId;
        this.processValue = processValue;
    }

    public Temperature getProcessValue()
    {
        return processValue;
    }

    public VesselId getVesselId()
    {
        return vesselId;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("ProcessValueChanged [processValue=").append(processValue)
                .append(", vesselId=").append(vesselId).append(", timestamp=").append(timestamp)
                .append("]");
        return builder.toString();
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((processValue == null) ? 0 : processValue.hashCode());
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
        final ProcessValueChanged other = (ProcessValueChanged) obj;
        if (processValue == null) {
            if (other.processValue != null) {
                return false;
            }
        } else if (!processValue.equals(other.processValue)) {
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
}
