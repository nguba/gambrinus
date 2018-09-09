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

import me.nguba.gambrinus.command.ChangeSetpoint;
import me.nguba.gambrinus.command.ReadTemperature;
import me.nguba.gambrinus.command.SetProcessValue;
import me.nguba.gambrinus.command.temperature.process.ProcessValueChanged;
import me.nguba.gambrinus.command.temperature.setpoint.SetpointChanged;
import me.nguba.gambrinus.cqrs.command.CommandProcessor;
import me.nguba.gambrinus.cqrs.handler.ChangeSetpointHandler;
import me.nguba.gambrinus.cqrs.handler.ReadTemperatureHandler;
import me.nguba.gambrinus.cqrs.handler.SetProcessValueHandler;
import me.nguba.gambrinus.cqrs.query.QueryProcessor;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.event.EventPublisher;
import me.nguba.gambrinus.process.Temperature;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Brewmaster
{
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

    private VesselRepository vessels;

    private EventPublisher events;

    public Brewmaster(final VesselRepository vessels, final EventPublisher events)
    {
        this.vessels = vessels;
        this.events = events;
    }

    public void heat(final VesselId vessel, final Temperature target) throws ValidationFailed
    {
        final ChangeSetpoint command = ChangeSetpoint.on(vessel, target);

        CommandProcessor.from(command, ChangeSetpointHandler.from(vessels)).mutate();

        events.publish(SetpointChanged.on(command.getId(), command.getSetpoint()));
    }

    public void monitor(final VesselId vessel, final Period period) throws ValidationFailed
    {
        // TODO same vessel cannot be monitored multiple times
        updateProcessValue(vessel);

        scheduler.scheduleAtFixedRate(() -> {
            try {
                updateProcessValue(vessel);
            } catch (final ValidationFailed e) {
                throw new IllegalStateException("Reading temperature failed", e);
            }
        }, period.getValue(), period.getValue(), TimeUnit.SECONDS);
    }

    public Temperature readProcessValue(final VesselId vessel) throws ValidationFailed
    {
        return QueryProcessor
                .query(ReadTemperature.from(vessel), ReadTemperatureHandler.on(vessels)).getResult()
                .orElse(Temperature.celsius(0));
    }
    
    private void updateProcessValue(final VesselId vessel) throws ValidationFailed
    {
        final Temperature currentTemp = QueryProcessor
                .query(ReadTemperature.from(vessel), ReadTemperatureHandler.on(vessels))
                .getResult()
                .orElse(Temperature.celsius(0));

        final SetProcessValue command = SetProcessValue.with(vessel, currentTemp);

        CommandProcessor.from(command, SetProcessValueHandler.from(vessels)).mutate();

        events.publish(ProcessValueChanged.on(command.getId(), command.getProcessValue()));
    }

}
