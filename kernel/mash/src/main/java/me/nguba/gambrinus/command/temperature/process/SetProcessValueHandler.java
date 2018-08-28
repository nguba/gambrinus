package me.nguba.gambrinus.command.temperature.process;

import me.nguba.gambrinus.command.VesselHandler;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselRepository;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 *
 */
public class SetProcessValueHandler extends VesselHandler<SetProcessValue>
{

  private SetProcessValueHandler(final VesselRepository repo)
  {
    super(repo);
  }

  public static SetProcessValueHandler from(final VesselRepository repo)
  {
    return new SetProcessValueHandler(repo);
  }

  @Override
  protected void onValidate(final SetProcessValue command, final Errors errors)
  {
    if (command.getProcessValue() == null)
      errors.add(Reason.from("No processValue"));
  }

  @Override
  protected void onVessel(final Vessel vessel, final SetProcessValue command)
  {
    vessel.processValue(command.getProcessValue());
  }
}
