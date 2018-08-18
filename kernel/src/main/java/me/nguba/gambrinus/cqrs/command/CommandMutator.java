package me.nguba.gambrinus.cqrs.command;

import me.nguba.gambrinus.ddd.validation.Errors;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public interface CommandMutator<C extends Command, E extends CommandEvent>
{
    void mutate(C command);

    E onCompletion(C command);

    void validate(C command, Errors errors);
}
