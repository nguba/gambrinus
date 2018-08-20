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
    private final ConcurrentHashMap<Object, MutatorFactory> mutators = new ConcurrentHashMap<>();

    private final EventPublisher publisher;

    public CommandProcessor(final EventPublisher publisher)
    {
        this.publisher = publisher;
    }

    public void register(final Class<? extends Command> command,
                         final MutatorFactory factory)
    {
        mutators.put(command, factory);
    }

    public boolean supports(final Class<? extends Command> command)
    {
        return mutators.get(command) != null;
    }

    public <C extends Command> void process(final C command) throws ValidationFailed
    {
        final Mutator<Command> mutator = getMutator(command);

        validate(command, mutator);

        mutator.mutate(command);

        publisher.publish(mutator.onCompletion(command));
    }

    private <C extends Command> Mutator<Command> getMutator(final C command)
    {
        final MutatorFactory factory = mutators.get(command.getClass());
        if (factory == null) {
            throw new UnsupportedOperationException(String.format("No factory registered for: %s",
                                                                  command.getClass().getName()));
        }

        @SuppressWarnings("unchecked")
        final Mutator<Command> mutator = (Mutator<Command>) factory.make();

        if (mutator == null) {
            throw new UnsupportedOperationException(String.format("No mutator for: %s",
                                                                  command.getClass()));
        }
        return mutator;
    }

    private static <C extends Command> void validate(final C command,
                                                     final Mutator<Command> mutator)
            throws ValidationFailed
    {
        final Errors errors = Errors.empty();

        mutator.validate(command, errors);

        errors.verify();
    }
}
