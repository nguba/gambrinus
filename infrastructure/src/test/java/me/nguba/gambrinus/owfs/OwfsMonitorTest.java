package me.nguba.gambrinus.owfs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.common.eventbus.Subscribe;

import me.nguba.gambrinus.GuavaEventPublisher;
import me.nguba.gambrinus.event.EventPublisher;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class OwfsMonitorTest
{
    private OwfsMonitor monitor;

    private final EventPublisher publisher = new GuavaEventPublisher();

    private final OwfsRoot root = OwfsRoot.of("src/test/resources/owfs");

    private final List<ProcessValueChanged> events = new ArrayList<>();

    @BeforeEach
    void setUp()
    {
        monitor = new OwfsMonitor(publisher);
        monitor.register(this);
    }

    @Test
    void broadcastTemperatureFromExpectedAddresses()
    {
    	assertThat(events).isEmpty();
    	 
        monitor.read(root);

        assertThat(events).hasSize(2);
    }

    @Subscribe
    public void onMonitorEvent(final ProcessValueChanged event)
    {
        events.add(event);
    }

}
