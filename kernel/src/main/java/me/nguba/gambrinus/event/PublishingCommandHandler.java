package me.nguba.gambrinus.event;

import me.nguba.gambrinus.cqrs.command.Command;
import me.nguba.gambrinus.cqrs.command.CommandHandler;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public interface PublishingCommandHandler<C extends Command> extends CommandHandler<C>
{
    MutatorEvent onCompletion(C command);
}
