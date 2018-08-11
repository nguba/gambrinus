package me.nguba.gambrinus.process;

import me.nguba.gambrinus.ddd.support.SingleValueObject;

import java.util.UUID;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class VesselId extends SingleValueObject<UUID>
{
    private VesselId(UUID value)
    {
        super(value);
    }

    public static VesselId generate()
    {
        return new VesselId(UUID.randomUUID());
    }
}
