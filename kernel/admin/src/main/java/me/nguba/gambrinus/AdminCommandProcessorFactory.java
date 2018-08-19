package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.command.CommandProcessor;
import me.nguba.gambrinus.cqrs.command.EventPublisher;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class AdminCommandProcessorFactory
{
    private final EventPublisher publisher;

    public AdminCommandProcessorFactory(final EventPublisher publisher)
    {
        this.publisher = publisher;
    }

    public CommandProcessor make()
    {
        final CommandProcessor processor = new CommandProcessor(publisher);
        return processor;
    }
}
