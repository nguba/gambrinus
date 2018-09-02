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

import me.nguba.gambrinus.command.temperature.process.SetProcessValue;
import me.nguba.gambrinus.command.temperature.setpoint.ChangeSetpoint;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.process.Temperature;
import me.nguba.gambrinus.query.temperature.read.ReadTemperature;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Brewmaster
{
    private final BrewCommands commands;

    private final BrewQueries queries;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

    public Brewmaster(final BrewCommands commands,
                      final BrewQueries queries)
    {
        this.commands = commands;
        this.queries = queries;
    }

    public void heat(final VesselId vessel, final Temperature target) throws ValidationFailed
    {
        commands.execute(ChangeSetpoint.on(vessel, target));
    }

    public Temperature processValue(final VesselId vessel) throws ValidationFailed
    {
        return queries.run(ReadTemperature.from(vessel));
    }

    public void monitor(final VesselId vessel, final Period period) throws ValidationFailed
    {
        updateProcessValue(vessel);

        scheduler.scheduleAtFixedRate(() -> {
            try {
                updateProcessValue(vessel);
            } catch (final ValidationFailed e) {
                throw new IllegalStateException("Reading temperature failed", e);
            }
        }, period.getValue(), period.getValue(), TimeUnit.SECONDS);
    }

    private void updateProcessValue(final VesselId vessel) throws ValidationFailed
    {
        final Temperature currentTemp = queries.run(ReadTemperature.from(vessel));
        commands.execute(SetProcessValue.with(vessel, currentTemp));
    }

}
