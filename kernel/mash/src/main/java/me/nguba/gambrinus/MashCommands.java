package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.command.CommandProcessor;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.event.EventPublisher;
import me.nguba.gambrinus.process.setpoint.ChangeSetpoint;
import me.nguba.gambrinus.process.setpoint.ChangeSetpointMutator;
import me.nguba.gambrinus.process.setpoint.SetpointChanged;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class MashCommands
{
    private final VesselRepository vessels;

    private final EventPublisher events;

    public MashCommands(final VesselRepository vessels, final EventPublisher events)
    {
        this.vessels = vessels;
        this.events = events;
    }

    public void execute(final ChangeSetpoint command) throws ValidationFailed
    {
        CommandProcessor.process(command, ChangeSetpointMutator.from(vessels));
        events.publish(SetpointChanged.on(command.getVesselId(), command.getSetpoint()));
    }
}
