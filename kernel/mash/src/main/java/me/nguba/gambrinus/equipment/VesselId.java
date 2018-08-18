package me.nguba.gambrinus.equipment;

import me.nguba.gambrinus.ddd.support.SingleValueObject;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class VesselId extends SingleValueObject<String>
{
    private VesselId(final String value)
    {
        super(value);
    }

    public static VesselId of(String name)
    {
        return new VesselId(name);
    }
}
