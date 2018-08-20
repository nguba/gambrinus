package me.nguba.gambrinus.equipment;

import me.nguba.gambrinus.ddd.Aggregate;
import me.nguba.gambrinus.owfs.OwfsSensor;
import me.nguba.gambrinus.process.Temperature;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Vessel extends Aggregate<VesselId>
{
    private Temperature setpoint = Temperature.celsius(0);

    private final Temperature processValue = Temperature.celsius(0);

    private OwfsSensor sensor;
    
    private Vessel(final VesselId id)
    {
        super(id);
    }

    public static Vessel of(final VesselId id)
    {
        return new Vessel(id);
    }

    public Temperature setpoint()
    {
        return setpoint;
    }

    public void setpoint(final Temperature setpoint)
    {
        this.setpoint = setpoint;
    }

    public Temperature processValue()
    {
        return processValue;
    }
    
    public void assign(OwfsSensor sensor) {
       this.sensor = sensor; 
    }
 }
