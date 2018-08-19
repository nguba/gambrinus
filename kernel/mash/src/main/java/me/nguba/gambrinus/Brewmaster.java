package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.command.CommandProcessor;
import me.nguba.gambrinus.cqrs.query.QueryProcessor;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.process.Temperature;
import me.nguba.gambrinus.process.query.ReadTemperature;
import me.nguba.gambrinus.process.query.ReadTemperatureResult;
import me.nguba.gambrinus.process.setpoint.ChangeSetpoint;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Brewmaster
{
    private final CommandProcessor commands;

    private final QueryProcessor queries;

    public Brewmaster(final MashCommandProcessorFactory commandFactory,
                      final MashQueryProcessorFactory queryFactory)
    {
        commands = commandFactory.make();
        queries = queryFactory.make();
    }

    public void heat(final VesselId vessel, final Temperature target) throws ValidationFailed
    {
        commands.process(ChangeSetpoint.on(vessel, target));
    }

    public Temperature readTemperature(final VesselId vessel) throws ValidationFailed
    {
        final ReadTemperatureResult result = queries.process(ReadTemperature.from(vessel));
        return result.getResult().get();
    }

 
}
