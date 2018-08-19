package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.command.CommandProcessor;
import me.nguba.gambrinus.cqrs.query.QueryProcessor;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.query.vessel.FindVessels;
import me.nguba.gambrinus.query.vessel.FindVesselsResult;

import java.util.Set;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class Administrator
{
    private final CommandProcessor commands;

    private final QueryProcessor queries;

    public Administrator(AdminCommandProcessorFactory commandFactory,
                         AdminQueryProcessorFactory queryFactory)
    {
        commands = commandFactory.make();
        queries = queryFactory.make();
    }

    public Set<Vessel> findVessels() throws ValidationFailed
    {
        final FindVesselsResult result = queries.process(FindVessels.create());
        return result.getResult().get();
    }
}
