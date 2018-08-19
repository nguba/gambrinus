package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.query.QueryProcessor;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.process.query.ReadTemperature;
import me.nguba.gambrinus.process.query.ReadTemperatureHandler;
import me.nguba.gambrinus.query.vessel.FindVessels;
import me.nguba.gambrinus.query.vessel.FindVesselsHandler;

public class MashQueryProcessorFactory
{
    private final VesselRepository vessels;

    public MashQueryProcessorFactory(final VesselRepository vessels)
    {
        this.vessels = vessels;
    }

    public QueryProcessor make()
    {
        final QueryProcessor processor = new QueryProcessor();
        processor.register(ReadTemperature.class, new ReadTemperatureHandler(vessels));
        processor.register(FindVessels.class, new FindVesselsHandler(vessels));
        return processor;
    }
}
