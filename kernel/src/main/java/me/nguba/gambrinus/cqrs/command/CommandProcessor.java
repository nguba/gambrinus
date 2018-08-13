package me.nguba.gambrinus.cqrs.command;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class CommandProcessor
{
    private final ConcurrentHashMap<Object, CommandMutator<?>> mutators = new ConcurrentHashMap<>();

    private final EventPublisher publisher;

    public CommandProcessor(final EventPublisher publisher)
    {
        this.publisher = publisher;
    }

    public void register(final Class<? extends Command<?>> command, final CommandMutator<?> mutator)
    {
        mutators.put(command, mutator);
    }

    public boolean supports(final Class<? extends Command<?>> command)
    {
        return mutators.containsKey(command);
    }

    public <C extends Command<?>> void execute(final C command)
    {
        @SuppressWarnings("unchecked")
        final CommandMutator<Command<?>> mutator = (CommandMutator<Command<?>>) mutators
                .get(command.getClass());
        if (mutator == null) {
            return;
        }

        if (command instanceof Validated) {
            ((Validated) command).validate();
        }

        mutator.mutate(command);

        publisher.publish(command.onCompletion());
    }
}
