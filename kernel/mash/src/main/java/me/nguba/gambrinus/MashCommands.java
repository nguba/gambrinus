package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.command.CommandProcessor;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.process.setpoint.ChangeSetpoint;
import me.nguba.gambrinus.process.setpoint.ChangeSetpointMutator;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class MashCommands
{
    private final VesselRepository vessels;

    public MashCommands(final VesselRepository vessels)
    {
        this.vessels = vessels;
    }

    public void execute(ChangeSetpoint command) throws ValidationFailed
    {
       CommandProcessor.process(command, ChangeSetpointMutator.from(vessels));
    }
}
