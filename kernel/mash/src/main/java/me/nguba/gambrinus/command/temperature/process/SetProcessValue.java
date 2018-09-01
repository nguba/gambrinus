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

import me.nguba.gambrinus.command.VesselCommand;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.process.Temperature;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 *
 */
public final class SetProcessValue implements VesselCommand
{
    private final VesselId vesselId;

    private final Temperature processValue;

    private SetProcessValue(final VesselId vesselId, final Temperature processValue)
    {
        this.vesselId = vesselId;
        this.processValue = processValue;
    }

    public static SetProcessValue with(final VesselId vesselId, final Temperature processValue)
    {
        return new SetProcessValue(vesselId, processValue);
    }

    @Override
    public VesselId getId()
    {
        return vesselId;
    }

    public Temperature getProcessValue()
    {
        return processValue;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (processValue == null ? 0 : processValue.hashCode());
        result = prime * result + (vesselId == null ? 0 : vesselId.hashCode());
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
        final SetProcessValue other = (SetProcessValue) obj;
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
