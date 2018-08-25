package me.nguba.gambrinus.query.temperature.read;

import me.nguba.gambrinus.cqrs.query.Query;
import me.nguba.gambrinus.equipment.VesselId;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ReadTemperature implements Query
{
    private final VesselId vesselId;

    private ReadTemperature(final VesselId vesselId)
    {
        this.vesselId = vesselId;
    }

    public static ReadTemperature from(final VesselId vesselId)
    {
        return new ReadTemperature(vesselId);
    }

    public VesselId getVesselId()
    {
        return vesselId;
    }

}
