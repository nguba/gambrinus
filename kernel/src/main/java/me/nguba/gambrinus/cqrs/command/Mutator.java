package me.nguba.gambrinus.cqrs.command;

import me.nguba.gambrinus.ddd.validation.Errors;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public interface Mutator<C extends Command>
{
    void mutate(C command);

    MutatorEvent onCompletion(C command);

    void validate(C command, Errors errors);

}
