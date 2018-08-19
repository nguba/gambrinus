package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.query.QueryProcessor;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.query.vessel.FindVessels;
import me.nguba.gambrinus.query.vessel.FindVesselsHandler;

public class AdminQueryProcessorFactory
{
    private final VesselRepository vessels;

    public AdminQueryProcessorFactory(final VesselRepository vessels)
    {
        this.vessels = vessels;
    }

    public QueryProcessor make()
    {
        final QueryProcessor processor = new QueryProcessor();
        processor.register(FindVessels.class, new FindVesselsHandler(vessels));
        return processor;
    }
}
