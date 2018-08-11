package me.nguba.gambrinus.process;

import me.nguba.gambrinus.ddd.Entity;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class MashTun extends Entity<VesselId>
{
    private MashTun(final VesselId id)
    {
        super(id);
    }

    public static MashTun create()
    {
        return new MashTun(VesselId.generate());
    }

    public static MashTun from(final VesselId id)
    {
        return new MashTun(id);
    }
}
