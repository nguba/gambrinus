package me.nguba.gambrinus.command.vessel.create;

import me.nguba.gambrinus.cqrs.command.CommandHandler;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.owfs.OwfsSensor;

import java.io.IOException;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class CreateVesselMutator implements CommandHandler<CreateVessel>
{
    private final VesselRepository repo;

    public CreateVesselMutator(final VesselRepository repo)
    {
        this.repo = repo;
    }

    @Override
    public void changeStateFor(final CreateVessel command)
    {
        try {
            repo.create(Vessel.of(command.getVesselId(),
                                  OwfsSensor.mount(command.getRoot(), command.getAddress())));
        } catch (final IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void validate(final CreateVessel command, final Errors errors)
    {
        if (command.getVesselId() == null) {
            errors.add(Reason.from("VesselId cannot be null"));
        }

        if (command.getAddress() == null) {
            errors.add(Reason.from("OneWireAddress cannot be null"));
        }

        if (command.getAddress() == null) {
            errors.add(Reason.from("OwfsRoot cannot be null"));
        }

        if (command.getRoot() != null && command.getAddress() != null) {
            try {
                OwfsSensor.mount(command.getRoot(), command.getAddress());
            } catch (final IOException e) {
                errors.add(Reason.from(e.getMessage()));
            }
        }

        if (repo.read(command.getVesselId()).isPresent()) {
            errors.add(Reason.from("Vessel already configured"));
        }
    }

}
