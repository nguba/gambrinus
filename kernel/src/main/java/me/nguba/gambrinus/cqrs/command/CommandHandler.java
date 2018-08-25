package me.nguba.gambrinus.cqrs.command;

import me.nguba.gambrinus.ddd.validation.Errors;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public interface CommandHandler<C extends Command>
{
  void changeStateFor(C command);

  void validate(C command, Errors errors);

}
