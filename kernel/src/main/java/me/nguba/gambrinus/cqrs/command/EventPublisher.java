package me.nguba.gambrinus.cqrs.command;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public interface EventPublisher
{
    <E extends CommandEvent> void publish(E onCompletion);
}
