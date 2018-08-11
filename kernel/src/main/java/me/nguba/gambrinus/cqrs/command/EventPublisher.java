package me.nguba.gambrinus.cqrs.command;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public interface EventPublisher
{
    void publish(CommandMutatedEvent event);
}
