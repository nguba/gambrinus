/*
 * Copyright (C) 2018 Nicolai P. Guba This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version. This program
 * is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details. You should have received a copy of the GNU General Public
 * License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package me.nguba.gambrinus.command;

import java.nio.file.Path;

import me.nguba.gambrinus.cqrs.command.Command;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.onewire.OneWireAddress;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class CreateVessel implements Command
{
    public static CreateVessel from(final VesselId vesselId,
                                    final Path root,
                                    final OneWireAddress address)
    {
        return new CreateVessel(vesselId, root, address);
    }

    private final OneWireAddress address;

    private final Path root;

    private final VesselId vesselId;

    private CreateVessel(final VesselId vesselId, final Path root, final OneWireAddress address)
    {
        this.vesselId = vesselId;
        this.root = root;
        this.address = address;
    }

    public OneWireAddress getAddress()
    {
        return address;
    }

    public Path getRoot()
    {
        return root;
    }

    public VesselId getVesselId()
    {
        return vesselId;
    }

}
