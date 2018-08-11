package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.ddd.Aggregate;

import java.io.IOException;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class OwfsSensor extends Aggregate<OwfsAddress>
{
    private OwfsMount mount;

    private OwfsSensor(OwfsMount mount, OwfsAddress address)
    {
        super(address);
        this.mount = mount;   
    }
    
    public static OwfsSensor mount(OwfsRoot root, OwfsAddress address) throws IOException
    {
        final OwfsMount mount = OwfsMount.from(root, address);
        if(!mount.isValid()) {
            throw new IOException("Mountpoint does not exist: " + mount.getValue());
        }
        return new OwfsSensor(mount, address);
    }

    public OwfsMount getMount()
    {
        return mount;
    }
   
}
