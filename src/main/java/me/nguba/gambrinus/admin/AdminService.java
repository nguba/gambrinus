package me.nguba.gambrinus.admin;

import me.nguba.gambrinus.Administrator;
import me.nguba.gambrinus.ddd.Service;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.owfs.OwfsRoot;

import org.springframework.hateoas.ResourceSupport;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Acts as anti-corruption layer for the administrator bounded context.
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class AdminService implements Service
{
    private final Administrator admin;

    public AdminService(final Administrator admin)
    {
        this.admin = admin;
    }

    public Set<Vessel> findVessels() throws ValidationFailed, IOException
    {
        return admin.findVessels();
    }

    public Set<ResourceSupport> findAddresses(final String mountpoint) throws ValidationFailed
    {
        final Set<OneWireAddress> findAddresses = admin.findAddresses(mountpoint);
        final Set<ResourceSupport> resources = new HashSet<>();
        findAddresses.forEach((address) -> {
            resources.add(SensorAddressAdapter.adapt(address));
        });
        return resources;
    }

    public ResourceSupport createVessel(VesselId id, OneWireAddress address, String mountpoint)
            throws ValidationFailed, IOException
    {
        Vessel vessel= admin.createVessel(id, OwfsRoot.of(mountpoint), address);
        return VesselAdapter.adapt(vessel);
    }

}
