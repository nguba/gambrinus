package me.nguba.gambrinus.command.vessel.create;

import me.nguba.gambrinus.cqrs.command.Command;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.onewire.OneWireAddress;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class CreateVessel implements Command
{
    private final VesselId vesselId;

    private final OneWireAddress address;

    private CreateVessel(final VesselId vesselId, final OneWireAddress address)
    {
        this.vesselId = vesselId;
        this.address = address;
    }

    public static CreateVessel from(final VesselId vesselId, final OneWireAddress sensor)
    {
        return new CreateVessel(vesselId, sensor);
    }

    protected VesselId getVesselId()
    {
        return vesselId;
    }

    protected OneWireAddress getAddress()
    {
        return address;
    }
}
