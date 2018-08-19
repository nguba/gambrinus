package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.command.CommandEvent;

import com.google.common.eventbus.Subscribe;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class GuavaEventPublisherTest
{
    private final GuavaEventPublisher publisher = new GuavaEventPublisher();

    private CommandEvent event;
    
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
    public void callback(CommandHappenedEvent event)
    {
        this.event = event;
    }
}
