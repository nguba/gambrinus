package me.nguba.gambrinus.admin;

import me.nguba.gambrinus.Administrator;
import me.nguba.gambrinus.ddd.Service;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.onewire.OneWireAddress;

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

    private final AdminConverter converter;

    public AdminService(final Administrator admin, final AdminConverter converter)
    {
        this.admin = admin;
        this.converter = converter;
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

}