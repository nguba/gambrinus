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

import me.nguba.gambrinus.command.SetProcessValue;
import me.nguba.gambrinus.command.VesselHandler;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselRepository;

import java.util.Optional;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 *
 */
public class SetProcessValueHandler extends VesselHandler<SetProcessValue>
{

    public static SetProcessValueHandler from(final VesselRepository repo)
    {
        return new SetProcessValueHandler(repo);
    }

    private SetProcessValueHandler(final VesselRepository repo)
    {
        super(repo);
    }

    @Override
    protected void onValidate(final SetProcessValue command, final Errors errors)
    {
        if (command.getProcessValue() == null)
            errors.add(Reason.from("No processValue"));

        if (command.getId() == null)
            errors.add(Reason.from("No vesselId"));

        final Optional<Vessel> read = repo.read(command.getId());
        if (!read.isPresent())
            errors.add(Reason.from(String.format("Vessel not found: %s", command.getId())));
        else if (!read.get().isActive())
            errors.add(Reason
                    .from(String.format("No sensor configured for: %s", command.getId())));
    }

    @Override
    protected void onVessel(final Vessel vessel, final SetProcessValue command)
    {
        vessel.processValue(command.getProcessValue());
    }
}
