package me.nguba.gambrinus.command.vessel.create;

import me.nguba.gambrinus.cqrs.command.CommandHandler;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.owfs.OwfsSensor;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class CreateVesselHandler implements CommandHandler<CreateVessel>
{
  private final VesselRepository repo;

  public CreateVesselHandler(final VesselRepository repo)
  {
    this.repo = repo;
  }

  @Override
  public void changeStateFor(final CreateVessel command)
  {
    repo.create(Vessel.of(command.getVesselId(),
                          OwfsSensor.from(command.getRoot(), command.getAddress())));
  }

  @Override
  public void validate(final CreateVessel command, final Errors errors)
  {
    if (command.getVesselId() == null)
      errors.add(Reason.from("VesselId cannot be null"));

    if (command.getAddress() == null)
      errors.add(Reason.from("OneWireAddress cannot be null"));

    if (command.getAddress() == null)
      errors.add(Reason.from("OwfsRoot cannot be null"));

    if (command.getRoot() != null && command.getAddress() != null) {
      final OwfsSensor sensor = OwfsSensor.from(command.getRoot(), command.getAddress());
      if (!sensor.isValid())
        errors.add(Reason.from("Invalid sensor: " + sensor.getPath()));
    }

    if (repo.read(command.getVesselId()).isPresent())
      errors.add(Reason.from("Vessel already configured"));
  }

}
