package me.nguba.gambrinus;

import me.nguba.gambrinus.event.EventPublisher;
import me.nguba.gambrinus.event.MutatorEvent;

import com.google.common.eventbus.EventBus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default event publisher that logs to the configured logging system but does not broadcast any
 * events to subscribers. Useful during testing.
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class GuavaEventPublisher implements EventPublisher
{
    private static final Logger LOGGER = LoggerFactory.getLogger(EventPublisher.class);

    private final EventBus bus = new EventBus();

    @Override
    public <E extends MutatorEvent> void publish(final E event)
    {
        LOGGER.trace("{}", event);
        bus.post(event);
    }

    @Override
    public void subscribe(final Object recipient)
    {
        bus.register(recipient);
    }

}
