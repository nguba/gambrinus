package me.nguba.gambrinus;

import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.process.Temperature;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ProcessValueChanged
{
    public static ProcessValueChanged on(VesselId vesselId, Temperature expected)
    {
        if (vesselId == null) {
            throw new IllegalArgumentException("VesselId cannot be null.");
        }
        return new ProcessValueChanged(vesselId, expected);
    }

    private Temperature processValue;

    private VesselId vesselId;

    private ProcessValueChanged(VesselId vesselId, Temperature processValue)
    {
        this.vesselId = vesselId;
        this.processValue = processValue == null ? Temperature.celsius(0) : processValue;
    }

    public Temperature getProcessValue()
    {
        return processValue;
    }

    public VesselId getVesselId()
    {
        return vesselId;
    }
}
