package me.nguba.gambrinus.command.temperature.setpoint;

import me.nguba.gambrinus.command.VesselHandler;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselRepository;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ChangeSetpointHandler extends VesselHandler<ChangeSetpoint>
{

  private ChangeSetpointHandler(final VesselRepository repo)
  {
    super(repo);
  }

  public static ChangeSetpointHandler from(final VesselRepository repo)
  {
    return new ChangeSetpointHandler(repo);
  }

  @Override
  protected void onVessel(final Vessel vessel, final ChangeSetpoint command)
  {
    vessel.setpoint(command.getSetpoint());
  }

  @Override
  protected void onValidate(final ChangeSetpoint command, final Errors errors)
  {
    if (command.getId() == null)
      errors.add(Reason.from("No vesselId"));
    else
      findVessel(command).ifPresent(vessel -> {
        if (!vessel.isActive())
          errors.add(Reason.from("No sensor assigned to vessel: " + vessel.getId()));
      });

    if (command.getSetpoint() == null)
      errors.add(Reason.from("No setpoint"));

  }

}
