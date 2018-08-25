package me.nguba.gambrinus;

import me.nguba.gambrinus.command.temperature.setpoint.ChangeSetpoint;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.process.Temperature;
import me.nguba.gambrinus.query.temperature.read.ReadTemperature;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Brewmaster
{
  private final MashCommands commands;

  private final MashQueries queries;

  public Brewmaster(final MashCommands commands,
                    final MashQueries queries)
  {
    this.commands = commands;
    this.queries = queries;
  }

  public void heat(final VesselId vessel, final Temperature target) throws ValidationFailed
  {
    commands.execute(ChangeSetpoint.on(vessel, target));
  }

  public Temperature readTemperature(final VesselId vessel) throws ValidationFailed
  {
    return queries.run(ReadTemperature.from(vessel));
  }

}
