package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.ddd.Aggregate;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class Owfs extends Aggregate<OwfsAddress>
{

    private Owfs(OwfsAddress address)
    {
        super(address);
    }
    
    public static Owfs of(OwfsAddress address)
    {
        return new Owfs(address);
    }

}
