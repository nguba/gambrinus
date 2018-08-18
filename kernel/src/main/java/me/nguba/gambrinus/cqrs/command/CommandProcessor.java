package me.nguba.gambrinus.cqrs.command;

import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class CommandProcessor
{
    private final ConcurrentHashMap<Object, CommandMutator<?, ?>> mutators = new ConcurrentHashMap<>();

    private final EventPublisher publisher;

    public CommandProcessor(final EventPublisher publisher)
    {
        this.publisher = publisher;
    }

    public void register(final Class<? extends Command> command,
                         final CommandMutator<?, ?> mutator)
    {
        mutators.put(command, mutator);
    }

    public boolean supports(final Class<? extends Command> command)
    {
        return mutators.containsKey(command);
    }

    public <C extends Command> void execute(final C command) throws ValidationFailed
    {
        @SuppressWarnings("unchecked")
        final CommandMutator<Command, ?> mutator = (CommandMutator<Command, ?>) mutators
                .get(command.getClass());

        if (mutator == null) {
            return;
        }

        validate(command, mutator);

        mutator.mutate(command);

        publisher.publish(mutator.onCompletion(command));
    }

    private static <C extends Command> void validate(final C command,
                                                     final CommandMutator<Command, ?> mutator)
            throws ValidationFailed
    {
        final Errors errors = Errors.empty();

        mutator.validate(command, errors);

        errors.verify();
    }
}
