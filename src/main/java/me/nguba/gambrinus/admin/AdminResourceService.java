package me.nguba.gambrinus.admin;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.hateoas.ResourceSupport;

import me.nguba.gambrinus.Administrator;
import me.nguba.gambrinus.ddd.Service;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.owfs.OwfsRoot;

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

  public Set<ResourceSupport> findVessels() throws ValidationFailed, IOException
  {
    Set<ResourceSupport> vessels = new HashSet<>();
    for (Vessel vessel : admin.findVessels()) {
      vessels.add(VesselAdapter.adapt(vessel));
    }
    return vessels;
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

  public void createVessel(final VesselId id,
                           final OneWireAddress address,
                           final String mountpoint)
      throws ValidationFailed, IOException
  {
    admin.createVessel(id, OwfsRoot.of(mountpoint), address);
  }

  public ResourceSupport findVessel(VesselId id) throws ValidationFailed
  {
    return VesselAdapter.adapt(admin.findVessel(id));
  }

}
