package me.nguba.gambrinus.command.vessel.create;

import me.nguba.gambrinus.cqrs.command.Command;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.owfs.OwfsRoot;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class CreateVessel implements Command
{
    private final VesselId vesselId;

    private final OwfsRoot root;

    private final OneWireAddress address;

    private CreateVessel(final VesselId vesselId, final OwfsRoot root, final OneWireAddress address)
    {
        this.vesselId = vesselId;
        this.root = root;
        this.address = address;
    }

    public static CreateVessel from(final VesselId vesselId,
                                    final OwfsRoot root,
                                    final OneWireAddress address)
    {
        return new CreateVessel(vesselId, root, address);
    }

    protected VesselId getVesselId()
    {
        return vesselId;
    }

    protected OwfsRoot getRoot()
    {
        return root;
    }

    protected OneWireAddress getAddress()
    {
        return address;
    }

}
