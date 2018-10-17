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

import me.nguba.gambrinus.command.CreateVessel;
import me.nguba.gambrinus.cqrs.command.CommandHandler;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.owfs.OwfsRoot;
import me.nguba.gambrinus.owfs.OwfsSensor;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class CreateVesselHandler implements CommandHandler<CreateVessel>
{
    private final VesselRepository repo;

    public CreateVesselHandler(final VesselRepository repo)
    {
        this.repo = repo;
    }

    @Override
    public void changeStateFor(final CreateVessel command)
    {
        repo.create(Vessel
                .of(command.getVesselId(),
                    OwfsSensor.from(OwfsRoot.of(command.getRoot()), command.getAddress())));
    }

    @Override
    public void validate(final CreateVessel command, final Errors errors)
    {
        if (command.getVesselId() == null)
            errors.add(Reason.from("VesselId cannot be null"));

        if (command.getAddress() == null)
            errors.add(Reason.from("OneWireAddress cannot be null"));

        if (command.getAddress() == null)
            errors.add(Reason.from("OwfsRoot cannot be null"));

        // TODO test for invalid owfsRoot

        if (command.getRoot() != null && command.getAddress() != null) {
            final OwfsSensor sensor = OwfsSensor.from(OwfsRoot.of(command.getRoot()),
                                                      command.getAddress());
            if (!sensor.isValid())
                errors.add(Reason.from("Invalid sensor: " + sensor));
        }

        if (repo.read(command.getVesselId()).isPresent())
            errors.add(Reason.from("Vessel already configured"));
    }

}
