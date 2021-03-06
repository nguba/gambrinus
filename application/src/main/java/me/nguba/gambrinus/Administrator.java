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
package me.nguba.gambrinus;

import me.nguba.gambrinus.command.CreateVessel;
import me.nguba.gambrinus.command.FindOneWireAddresses;
import me.nguba.gambrinus.command.FindVessel;
import me.nguba.gambrinus.command.FindVessels;
import me.nguba.gambrinus.cqrs.command.CommandProcessor;
import me.nguba.gambrinus.cqrs.handler.CreateVesselHandler;
import me.nguba.gambrinus.cqrs.handler.FindOneWireAddressesHandler;
import me.nguba.gambrinus.cqrs.handler.FindVesselHandler;
import me.nguba.gambrinus.cqrs.handler.FindVesselsHandler;
import me.nguba.gambrinus.cqrs.query.QueryProcessor;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.onewire.OneWireAddress;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class Administrator
{
    private final VesselRepository repo;

    public Administrator(final VesselRepository repo)
    {
        this.repo = repo;
    }

    public Vessel createVessel(final VesselId vesselId,
                               final Path mountpoint,
                               final OneWireAddress address)
            throws ValidationFailed
    {
        final CreateVessel command = CreateVessel.from(vesselId, mountpoint, address);
        CommandProcessor.mutate(command, new CreateVesselHandler(repo));

        return findVessel(vesselId);
    }

    public Set<OneWireAddress> findAddresses(final String mountpoint) throws ValidationFailed
    {
        final FindOneWireAddresses query = FindOneWireAddresses.on(mountpoint);
        return QueryProcessor.query(query, FindOneWireAddressesHandler.create());
    }

    public Vessel findVessel(final VesselId id) throws ValidationFailed
    {
        final FindVessel query  = FindVessel.of(id);
        final Vessel     result = QueryProcessor.query(query, FindVesselHandler.on(repo)).get();
        return result;
    }

    public Set<Vessel> findVessels() throws ValidationFailed, IOException
    {
        return QueryProcessor.query(FindVessels.create(), FindVesselsHandler.on(repo));
    }
}
