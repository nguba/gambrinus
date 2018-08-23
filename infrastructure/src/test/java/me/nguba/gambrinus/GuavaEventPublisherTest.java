package me.nguba.gambrinus;

import me.nguba.gambrinus.event.MutatorEvent;

import com.google.common.eventbus.Subscribe;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class GuavaEventPublisherTest
{
    private final GuavaEventPublisher publisher = new GuavaEventPublisher();

    private MutatorEvent event;

    @Test
    void publishSubscribed()
    {
        publisher.subscribe(this);

        publisher.publish(new CommandHappenedEvent());

        assertThat(event).isInstanceOf(CommandHappenedEvent.class);
    }

    @Test
    void publishNotSubscribed()
    {

        publisher.publish(new CommandHappenedEvent());

        assertThat(event).isNull();
    }

    @Subscribe
    public void callback(final CommandHappenedEvent event)
    {
        this.event = event;
    }
}
