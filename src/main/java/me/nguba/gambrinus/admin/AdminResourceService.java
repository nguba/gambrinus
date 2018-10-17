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
package me.nguba.gambrinus.admin;

import me.nguba.gambrinus.Administrator;
import me.nguba.gambrinus.ddd.Service;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.onewire.OneWireAddress;

import org.springframework.hateoas.ResourceSupport;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * Acts as anti-corruption layer for the administrator bounded context.
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class AdminResourceService implements Service
{
    private final Administrator admin;

    public AdminResourceService(final Administrator admin)
    {
        this.admin = admin;
    }

    public void createVessel(final VesselId id, final OneWireAddress address, final Path mountpoint)
            throws ValidationFailed, IOException
    {
        admin.createVessel(id, mountpoint, address);
    }

    public Set<ResourceSupport> findAddresses(final String mountpoint) throws ValidationFailed
    {
        final Set<OneWireAddress>  findAddresses = admin.findAddresses(mountpoint);
        final Set<ResourceSupport> resources     = new HashSet<>();
        findAddresses.forEach((address) -> {
            resources.add(SensorAddressAdapter.adapt(address));
        });
        return resources;
    }

    public ResourceSupport findVessel(final VesselId id) throws ValidationFailed
    {
        return VesselAdapter.adapt(admin.findVessel(id));
    }

    public Set<ResourceSupport> findVessels() throws ValidationFailed, IOException
    {
        final Set<ResourceSupport> vessels = new HashSet<>();
        for (final Vessel vessel : admin.findVessels())
            vessels.add(VesselAdapter.adapt(vessel));
        return vessels;
    }

}
