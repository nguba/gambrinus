package me.nguba.gambrinus.process.setpoint;

import me.nguba.gambrinus.cqrs.command.CommandMutator;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselRepository;

import java.util.Optional;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ChangeSetpointMutator implements CommandMutator<ChangeSetpoint, SetpointChanged>
{
    private final VesselRepository repo;

    public ChangeSetpointMutator(final VesselRepository repo)
    {
        this.repo = repo;
    }

    @Override
    public void mutate(final ChangeSetpoint command)
    {
        if (command == null) {
            return;
        }

        final Vessel vessel = findVessel(command);
        vessel.setpoint(command.getSetpoint());
    }

    @Override
    public SetpointChanged onCompletion(final ChangeSetpoint command)
    {
        return SetpointChanged.on(command.getVesselId(), command.getSetpoint());
    }

    @Override
    public void validate(final ChangeSetpoint command, final Errors errors)
    {
        if (command.getVesselId() == null) {
            errors.add(Reason.from("No vesselId"));
        }

        if (command.getSetpoint() == null) {
            errors.add(Reason.from("No setpoint"));
        }
    }

    private Vessel findVessel(final ChangeSetpoint command)
    {
        final Optional<Vessel> vessel = repo.read(command.getVesselId());
        return vessel.orElseThrow(() -> new IllegalArgumentException("Unable to find vessel: "
                + command.getVesselId()));
    }

}