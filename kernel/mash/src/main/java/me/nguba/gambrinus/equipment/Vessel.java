package me.nguba.gambrinus.equipment;

import me.nguba.gambrinus.ddd.Aggregate;
import me.nguba.gambrinus.process.Temperature;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Vessel extends Aggregate<VesselId>
{
    private Temperature setpoint;

    private Vessel(final VesselId id)
    {
        super(id);
    }

    public static Vessel of(final VesselId id)
    {
        final Vessel vessel = new Vessel(id);
        vessel.setpoint(Temperature.celsius(0));
        return vessel;
    }

    public Temperature setpoint()
    {
        return setpoint;
    }

    public void setpoint(final Temperature setpoint)
    {
        this.setpoint = setpoint;
    }
}
