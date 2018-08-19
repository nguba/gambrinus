package me.nguba.gambrinus.query.vessel;

import me.nguba.gambrinus.cqrs.query.Query;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public enum FindVessels implements Query
{
    INSTANCE;

    public static FindVessels create()
    {
        return INSTANCE;
    }
}
