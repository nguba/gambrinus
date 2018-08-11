package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.ddd.Aggregate;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class OwfsSensor extends Aggregate<OwfsAddress>
{

    private OwfsSensor(OwfsAddress address)
    {
        super(address);
    }
    
    public static OwfsSensor of(OwfsAddress address)
    {
        return new OwfsSensor(address);
    }

}
