package me.nguba.gambrinus;

import java.io.IOException;
import java.util.Set;

import me.nguba.gambrinus.command.vessel.create.CreateVessel;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.owfs.OwfsRoot;
import me.nguba.gambrinus.query.onewire.FindOneWireAddresses;
import me.nguba.gambrinus.query.vessel.FindVessels;
import me.nguba.gambrinus.query.vessel.find.FindVessel;

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

  public Set<Vessel> findVessels() throws ValidationFailed, IOException
  {
    return queries.run(FindVessels.create());
  }

  public Set<OneWireAddress> findAddresses(final String mountpoint) throws ValidationFailed
  {
    return queries.run(FindOneWireAddresses.on(mountpoint));
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

  public Vessel findVessel(final VesselId id) throws ValidationFailed
  {
    return queries.run(FindVessel.of(id));
  }
}
