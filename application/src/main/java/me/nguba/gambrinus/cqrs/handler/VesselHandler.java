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

import java.util.Optional;

import me.nguba.gambrinus.command.VesselCommand;
import me.nguba.gambrinus.cqrs.command.CommandHandler;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselRepository;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public abstract class VesselHandler<C extends VesselCommand> implements CommandHandler<C>
{

    protected final VesselRepository repo;

    protected VesselHandler(final VesselRepository repo)
    {
        this.repo = repo;
    }

    @Override
    public final void changeStateFor(final C command)
    {
        commandNotNull(command);

        final Optional<Vessel> vessel = findVessel(command);
        if (vessel.isPresent())
            onVessel(vessel.get(), command);
        else throw new IllegalArgumentException(vesselNotFound(command));

    }

    protected void commandNotNull(final C command)
    {
        if (command == null)
            throw new IllegalArgumentException("Command cannot be null");
    }

    protected Optional<Vessel> findVessel(final C command)
    {
        return repo.read(command.getId());
    }

    protected abstract void onValidate(C command, Errors errors);

    protected abstract void onVessel(Vessel vessel, C command);

    @Override
    public final void validate(final C command, final Errors errors)
    {
        commandNotNull(command);

        onValidate(command, errors);

        if (command.getId() != null) {
            final Optional<Vessel> vessel = findVessel(command);
            if (!vessel.isPresent())
                errors.add(Reason.from(vesselNotFound(command)));
        } else errors.add(Reason.from("No vesselId"));

    }

    private String vesselNotFound(final C command)
    {
        return String.format("Vessel not found: %s", command.getId());
    }

}
