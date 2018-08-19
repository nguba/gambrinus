package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.GuavaEventPublisher;
import me.nguba.gambrinus.ProcessValueChanged;
import me.nguba.gambrinus.cqrs.command.EventPublisher;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.process.Temperature;

import com.google.common.eventbus.Subscribe;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class OwfsMonitorTest
{
    private OwfsMonitor monitor;

    private final EventPublisher publisher = new GuavaEventPublisher();

    private final OwfsRoot root = OwfsRoot.of("src/test/resources/owfs");

    private List<ProcessValueChanged> events = new ArrayList<>();
    
    @BeforeEach
    void setUp()
    {
        monitor = new OwfsMonitor(publisher);
        monitor.register(this);
    }

    @Test
    void broadcastTemperatureFromExpectedAddresses()
    {
        monitor.read(root);
        
        assertThat(events).containsOnlyOnce(ProcessValueChanged.on(OneWireAddress.of("28.273B5D070000"), Temperature.celsius(25.7)),
                                            ProcessValueChanged.on(OneWireAddress.of("28.4BBB68080000"), Temperature.celsius(27.3)));
    }

    @Subscribe
    public void onMonitorEvent(final ProcessValueChanged event)
    {
        events.add(event);
    }

}
