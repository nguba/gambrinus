/*
    Copyright (C) 2018  Nicolai P. Guba

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package me.nguba.gambrinus.cqrs.handler;

import me.nguba.gambrinus.command.ChangeSetpoint;
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

    public static ChangeSetpointHandler from(final VesselRepository repo)
    {
        return new ChangeSetpointHandler(repo);
    }

    private ChangeSetpointHandler(final VesselRepository repo)
    {
        super(repo);
    }

    @Override
    protected void onValidate(final ChangeSetpoint command, final Errors errors)
    {
        if (command.getId() == null)
            errors.add(Reason.from("No vesselId"));
        else findVessel(command).ifPresent(vessel -> {
            if (!vessel.isActive())
                errors.add(Reason.from("No sensor assigned to vessel: " + vessel.getId()));
        });

        if (command.getSetpoint() == null)
            errors.add(Reason.from("No setpoint"));

    }

    @Override
    protected void onVessel(final Vessel vessel, final ChangeSetpoint command)
    {
        vessel.setpoint(command.getSetpoint());
    }

}
