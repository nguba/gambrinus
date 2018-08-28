package me.nguba.gambrinus;

import me.nguba.gambrinus.command.vessel.create.CreateVessel;
import me.nguba.gambrinus.command.vessel.create.CreateVesselHandler;
import me.nguba.gambrinus.cqrs.command.CommandProcessor;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.VesselRepository;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class AdminCommands
{
  private final VesselRepository repo;

  public AdminCommands(final VesselRepository repo)
  {
    this.repo = repo;
  }

  public void execute(final CreateVessel command) throws ValidationFailed
  {
    CommandProcessor.from(command, new CreateVesselHandler(repo)).mutate();
  }
}
