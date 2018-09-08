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
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.owfs.OwfsRoot;

import java.io.IOException;
import java.util.Set;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class Administrator
{
    private final AdminCommands commands;

    private final AdminQueries queries;

    public Administrator(final AdminCommands commands,
                         final AdminQueries queries)
    {
        this.commands = commands;
        this.queries = queries;
    }

    public Vessel createVessel(final VesselId vesselId,
                               final OwfsRoot root,
                               final OneWireAddress address)
            throws ValidationFailed
    {
        commands.execute(CreateVessel.from(vesselId, root, address));

        final Set<Vessel> result = queries.run(FindVessels.create());
        return result.iterator().next();
    }

    public Set<OneWireAddress> findAddresses(final String mountpoint) throws ValidationFailed
    {
        return queries.run(FindOneWireAddresses.on(mountpoint));
    }

    public Vessel findVessel(final VesselId id) throws ValidationFailed
    {
        return queries.run(FindVessel.of(id));
    }

    public Set<Vessel> findVessels() throws ValidationFailed, IOException
    {
        return queries.run(FindVessels.create());
    }
}
