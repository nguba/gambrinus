package me.nguba.gambrinus.domain.cqrs.command;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public interface EventPublisher
{
    void publish(CommandMutatedEvent event);
}
