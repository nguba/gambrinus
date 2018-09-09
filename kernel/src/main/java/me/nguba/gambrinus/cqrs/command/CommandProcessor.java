/*
 * Copyright (C) 2018 Nicolai P. Guba This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version. This program
 * is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details. You should have received a copy of the GNU General Public
 * License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
    public static <C extends Command> CommandProcessor<C> from(final C command,
                                                               final CommandHandler<C> handler)
    {
        return new CommandProcessor<>(command, handler);
    }

    private final C command;

    private final CommandHandler<C> handler;

    public CommandProcessor(final C command, final CommandHandler<C> handler)
    {
        CqrsUtil.notNull(command, "Command cannot be null");
        CqrsUtil.notNull(handler, "Handler cannot be null");

        this.command = command;
        this.handler = handler;
    }

    public void mutate() throws ValidationFailed
    {
        final Errors errors = Errors.empty();

        handler.validate(command, errors);

        errors.verify();

        handler.changeStateFor(command);
    }

    public static <C extends Command> void mutate(final C command, CommandHandler<C> handler)
            throws ValidationFailed
    {
        CommandProcessor.from(command, handler).mutate();
    }
}
