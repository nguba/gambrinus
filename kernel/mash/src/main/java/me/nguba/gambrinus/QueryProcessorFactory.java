package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.query.QueryProcessor;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.process.query.ReadTemperature;
import me.nguba.gambrinus.process.query.ReadTemperatureHandler;

public class QueryProcessorFactory
{
    private final VesselRepository vessels;

    public QueryProcessorFactory(final VesselRepository vessels)
    {
        this.vessels = vessels;
    }

    public QueryProcessor make()
    {
        final QueryProcessor processor = new QueryProcessor();
        processor.register(ReadTemperature.class, new ReadTemperatureHandler(vessels));
        return processor;
    }
}
