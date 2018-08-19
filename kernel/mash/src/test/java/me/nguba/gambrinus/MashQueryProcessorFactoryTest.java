package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.query.QueryProcessor;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.process.query.ReadTemperature;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MashQueryProcessorFactoryTest
{
    private final QueryProcessor processor = new MashQueryProcessorFactory(new VesselRepository())
            .make();

    @Test
    void supportsReadTemperature()
    {
        assertThat(processor.supports(ReadTemperature.class));
    }
}
