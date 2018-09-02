package me.nguba.gambrinus.event;

import me.nguba.gambrinus.InfrastructureTest;
import me.nguba.gambrinus.command.temperature.process.ProcessValueChanged;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.event.EventPublisher;
import me.nguba.gambrinus.event.EventStore;
import me.nguba.gambrinus.event.sources.EventSource;
import me.nguba.gambrinus.event.sources.ProcessValueChangedSource;
import me.nguba.gambrinus.process.Temperature;

import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@InfrastructureTest
class EventSourceMediatorTest
{
    @Autowired
    private EventStore eventStore;

    @Autowired
    private EventPublisher publisher;

    private <E extends EventSource> int countEvents(final Class<E> target)
    {
        return eventStore.find(target).size();
    }

    @Test
    void monitorsProcessValueChanged()
    {
        final int before = countEvents(ProcessValueChangedSource.class);

        publisher.publish(ProcessValueChanged.on(VesselId.of("teapot"), Temperature.celsius(100)));

        final int after = countEvents(ProcessValueChangedSource.class);

        assertThat(after).isGreaterThan(before);
    }
}
