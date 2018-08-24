package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.event.EventPublisher;
import me.nguba.gambrinus.process.Temperature;

import java.io.IOException;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class OwfsMonitor
{
    private final EventPublisher publisher;

    public OwfsMonitor(final EventPublisher publisher)
    {
        this.publisher = publisher;
    }

    public void read(final OwfsRoot root)
    {
        for (final OwfsSensor sensor : root.listSensors()) {
            try {
                final Temperature read = sensor.read();
                publisher.publish(ProcessValueChanged.on(sensor.getId(), read));
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void register(final Object recipient)
    {
        publisher.subscribe(recipient);
    }
}
