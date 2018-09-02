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

import me.nguba.gambrinus.command.temperature.setpoint.VesselMutatorEvent;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.event.MutatorEvent;
import me.nguba.gambrinus.process.Temperature;

import java.time.Instant;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ProcessValueChanged extends VesselMutatorEvent
{
    private final Temperature processValue;

    private ProcessValueChanged(final Instant now,
                                final VesselId vesselId,
                                final Temperature processValue)
    {
        super(now, vesselId);
        this.processValue = processValue;
    }

    public static MutatorEvent on(final VesselId id, final Temperature processValue)
    {
        return new ProcessValueChanged(Instant.now(), id, processValue);
    }

    public Temperature getProcessValue()
    {
        return processValue;
    }

}
