package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.command.CommandProcessor;
import me.nguba.gambrinus.cqrs.query.QueryProcessor;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.query.onewire.FindOneWireAddressResult;
import me.nguba.gambrinus.query.onewire.FindOneWireAddresses;
import me.nguba.gambrinus.query.vessel.FindVessels;
import me.nguba.gambrinus.query.vessel.FindVesselsResult;

import java.io.IOException;
import java.util.Set;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class Administrator
{
    private final CommandProcessor commands;

    private final QueryProcessor queries;

    public Administrator(final AdminCommandProcessorFactory commandFactory,
                         final AdminQueryProcessorFactory queryFactory)
    {
        commands = commandFactory.make();
        queries = queryFactory.make();
    }

    public Set<Vessel> findVessels() throws ValidationFailed, IOException
    {
        final FindVesselsResult result = queries.process(FindVessels.create());
        return result.getResult().get();
    }

    public Set<OneWireAddress> findAddresses(final String mountpoint) throws ValidationFailed
    {
        final FindOneWireAddressResult result = queries
                .process(FindOneWireAddresses.on(mountpoint));
        return result.getResult().get();
    }
}