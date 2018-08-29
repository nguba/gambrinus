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
package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.command.temperature.process.SetProcessValue;
import me.nguba.gambrinus.command.temperature.process.SetProcessValueHandler;
import me.nguba.gambrinus.cqrs.command.CommandProcessor;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.process.Temperature;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class OwfsMonitor
{
    private final Map<OneWireAddress, VesselId> assignments = new ConcurrentHashMap<>();

    private final VesselRepository repository;

    public OwfsMonitor(final VesselRepository repository)
    {
        this.repository = repository;
    }

    public void read(final OwfsRoot root)
    {
        for (final OwfsSensor sensor : root.listSensors()) {
            if (isAssigned(sensor)) {
                try {
                    final Optional<Temperature> read = sensor.read();
                    if (read.isPresent()) {
                        CommandProcessor
                                .from(SetProcessValue.on(getRegisteredVessel(sensor), read.get()),
                                      SetProcessValueHandler.from(repository))
                                .mutate();
                    }
                } catch (final IOException e) {
                    e.printStackTrace();
                } catch (final ValidationFailed e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean isAssigned(final OwfsSensor sensor)
    {
        return getRegisteredVessel(sensor) != null;
    }

    private VesselId getRegisteredVessel(final OwfsSensor sensor)
    {
        return assignments.get(sensor.getId());
    }

    public void assign(final OneWireAddress address, final VesselId vesselId)
    {
        assignments.put(address, vesselId);
    }

    public VesselId vesselFor(final OneWireAddress address)
    {
        return assignments.get(address);
    }
}
