package me.nguba.gambrinus.cqrs.command;

import me.nguba.gambrinus.cqrs.CqrsUtil;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class CommandProcessor<C extends Command>
{
    private final C                 command;
    private final CommandHandler<C> handler;

    public CommandProcessor(final C command, final CommandHandler<C> handler)
    {
        CqrsUtil.notNull(command, "Command cannot be null");
        CqrsUtil.notNull(handler, "Handler cannot be null");

        this.command = command;
        this.handler = handler;
    }

    public static <C extends Command> CommandProcessor<C> from(final C command,
                                                               final CommandHandler<C> handler)
    {
        return new CommandProcessor<C>(command, handler);
    }

    public void mutate() throws ValidationFailed
    {
        final Errors errors = Errors.empty();

        handler.validate(command, errors);

        errors.verify();

        handler.changeStateFor(command);
    }
}
