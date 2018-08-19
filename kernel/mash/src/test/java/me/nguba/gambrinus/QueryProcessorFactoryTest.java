package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.query.QueryProcessor;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.process.query.ReadTemperature;
import me.nguba.gambrinus.query.vessel.FindVessels;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class QueryProcessorFactoryTest
{
    private final QueryProcessor processor = new QueryProcessorFactory(new VesselRepository()).make();

    @Test
    void supportsReadTemperature()
    {
        assertThat(processor.supports(ReadTemperature.class));
    }
    
    @Test
    void supportsFindVessels()
    {
        assertThat(processor.supports(FindVessels.class));
    }

}
