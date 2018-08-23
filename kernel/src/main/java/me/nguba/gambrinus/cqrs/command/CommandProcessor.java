package me.nguba.gambrinus.cqrs.command;

import me.nguba.gambrinus.cqrs.CqrsUtil;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class CommandProcessor
{
    private CommandProcessor()
    {
        super();
    }

    public static <C extends Command> void process(final C command, final CommandHandler<C> handler)
            throws ValidationFailed
    {
        CqrsUtil.notNull(command, "Command cannot be null");

        CqrsUtil.notNull(handler, "Handler cannot be null");

        validate(command, handler);

        handler.changeStateFor(command);
    }

    private static <C extends Command> void validate(final C command,
                                                     final CommandHandler<C> mutator)
            throws ValidationFailed
    {
        final Errors errors = Errors.empty();

        mutator.validate(command, errors);

        errors.verify();
    }
}
