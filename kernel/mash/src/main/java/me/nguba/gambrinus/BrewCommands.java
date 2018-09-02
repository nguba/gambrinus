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
package me.nguba.gambrinus;

import me.nguba.gambrinus.command.temperature.process.ProcessValueChanged;
import me.nguba.gambrinus.command.temperature.process.SetProcessValue;
import me.nguba.gambrinus.command.temperature.process.SetProcessValueHandler;
import me.nguba.gambrinus.command.temperature.setpoint.ChangeSetpoint;
import me.nguba.gambrinus.command.temperature.setpoint.ChangeSetpointHandler;
import me.nguba.gambrinus.command.temperature.setpoint.SetpointChanged;
import me.nguba.gambrinus.cqrs.command.CommandProcessor;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.event.EventPublisher;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class BrewCommands
{
    private final EventPublisher events;

    private final VesselRepository vessels;

    public BrewCommands(final VesselRepository vessels, final EventPublisher events)
    {
        this.vessels = vessels;
        this.events = events;
    }

    public void execute(final ChangeSetpoint command) throws ValidationFailed
    {
        CommandProcessor.from(command, ChangeSetpointHandler.from(vessels)).mutate();
        events.publish(SetpointChanged.on(command.getId(), command.getSetpoint()));
    }

    public void execute(final SetProcessValue command) throws ValidationFailed
    {
        CommandProcessor.from(command, SetProcessValueHandler.from(vessels)).mutate();
        events.publish(ProcessValueChanged.on(command.getId(), command.getProcessValue()));
    }
}
