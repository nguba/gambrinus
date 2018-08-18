package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.command.CommandProcessor;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.process.Temperature;
import me.nguba.gambrinus.process.setpoint.ChangeSetpoint;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Brewmaster
{
    private final CommandProcessor commands;

    public Brewmaster(final CommandProcessor commands)
    {
        this.commands = commands;
    }

    public void heat(final VesselId vessel, final Temperature target) throws ValidationFailed
    {
        commands.execute(ChangeSetpoint.on(vessel, target));
    }
}
