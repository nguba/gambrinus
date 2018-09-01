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
package me.nguba.gambrinus.equipment;

import me.nguba.gambrinus.ddd.Aggregate;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.owfs.OwfsSensor;
import me.nguba.gambrinus.process.Temperature;

import java.io.IOException;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Vessel extends Aggregate<VesselId>
{
    private Temperature setpoint = Temperature.celsius(0);

    private Temperature processValue = Temperature.celsius(0);

    private OwfsSensor sensor;

    private Vessel(final VesselId id, final OwfsSensor sensor)
    {
        super(id);
        this.sensor = sensor;
    }

    /**
     * Returns a vessel without assigned sensor.
     *
     * @param id
     * @return the inactive vessel
     */
    public static Vessel inactive(final VesselId id)
    {
        return new Vessel(id, null);
    }

    public static Vessel of(final VesselId id, final OwfsSensor sensor)
    {
        return new Vessel(id, sensor);
    }

    public Temperature setpoint()
    {
        return setpoint;
    }

    public void setpoint(final Temperature setpoint)
    {
        this.setpoint = setpoint;
    }

    public Temperature processValue()
    {
        return processValue;
    }

    public void processValue(final Temperature processValue)
    {
        this.processValue = processValue;
    }

    public void assign(final OwfsSensor sensor)
    {
        this.sensor = sensor;
    }

    public Temperature readTemperature() throws IOException
    {
        if (!isActive()) {
            throw new IllegalStateException("Cannot read from inactive sensor");
        }
        return sensor.read().get();
    }

    public OneWireAddress address()
    {
        if (sensor == null) {
            return OneWireAddress.empty();
        }

        return sensor.getId();
    }

    public boolean isActive()
    {
        return sensor != null;
    }
}
