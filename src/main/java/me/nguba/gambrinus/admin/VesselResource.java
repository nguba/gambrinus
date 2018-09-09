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
package me.nguba.gambrinus.admin;

import org.springframework.hateoas.ResourceSupport;

import me.nguba.gambrinus.equipment.Vessel;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 *
 */
public final class VesselResource extends ResourceSupport
{
    private final String processValue;
    private final String sensor;
    private final String setpoint;

    public VesselResource(final Vessel entity)
    {
        sensor = entity.address().isValid() ? entity.address().toString() : null;
        processValue = entity.processValue().toString();
        setpoint = entity.setpoint().toString();
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
        final VesselResource other = (VesselResource) obj;
        if (sensor == null) {
            if (other.sensor != null)
                return false;
        } else if (!sensor.equals(other.sensor))
            return false;
        if (processValue == null) {
            if (other.processValue != null)
                return false;
        } else if (!processValue.equals(other.processValue))
            return false;
        if (setpoint == null) {
            if (other.setpoint != null)
                return false;
        } else if (!setpoint.equals(other.setpoint))
            return false;
        return true;
    }

    public String getProcessValue()
    {
        return processValue;
    }

    public String getSensor()
    {
        return sensor;
    }

    public String getSetpoint()
    {
        return setpoint;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (sensor == null ? 0 : sensor.hashCode());
        result = prime * result + (processValue == null ? 0 : processValue.hashCode());
        result = prime * result + (setpoint == null ? 0 : setpoint.hashCode());
        return result;
    }

}
