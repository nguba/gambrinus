package me.nguba.gambrinus;

import me.nguba.gambrinus.command.temperature.setpoint.ChangeSetpoint;
import me.nguba.gambrinus.command.temperature.setpoint.ChangeSetpointHandler;
import me.nguba.gambrinus.command.temperature.setpoint.SetpointChanged;
import me.nguba.gambrinus.cqrs.command.CommandProcessor;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.event.EventPublisher;

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
    CommandProcessor.from(command, ChangeSetpointHandler.from(vessels)).mutate();
    events.publish(SetpointChanged.on(command.getId(), command.getSetpoint()));
  }
}
