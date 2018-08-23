package me.nguba.gambrinus.event;

import me.nguba.gambrinus.cqrs.command.Command;
import me.nguba.gambrinus.cqrs.command.CommandProcessor;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class PublishinCommandProcessor
{
    private final EventPublisher publisher;

    private PublishinCommandProcessor(final EventPublisher publisher)
    {
        super();
        this.publisher = publisher;
    }

    public static PublishinCommandProcessor create(final EventPublisher publisher)
    {
        return new PublishinCommandProcessor(publisher);
    }

    public <C extends Command> void process(final C command,
                                            final PublishingCommandHandler<C> handler)
            throws ValidationFailed
    {
        CommandProcessor.process(command, handler);

        publisher.publish(handler.onCompletion(command));
    }
}
