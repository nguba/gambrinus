package me.nguba.gambrinus;


import me.nguba.gambrinus.cqrs.command.CommandEvent;
import me.nguba.gambrinus.cqrs.command.EventPublisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class DefaultEventPublisher implements EventPublisher
{
    private static final Logger LOGGER = LoggerFactory.getLogger(EventPublisher.class);
    
    @Override
    public <E extends CommandEvent> void publish(E event)
    {
        LOGGER.info("{}", event);
    }

}
