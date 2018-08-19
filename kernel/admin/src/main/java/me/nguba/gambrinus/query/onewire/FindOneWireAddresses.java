package me.nguba.gambrinus.query.onewire;

import me.nguba.gambrinus.cqrs.query.Query;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class FindOneWireAddresses implements Query
{
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mountpoint == null) ? 0 : mountpoint.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FindOneWireAddresses other = (FindOneWireAddresses) obj;
        if (mountpoint == null) {
            if (other.mountpoint != null) {
                return false;
            }
        } else if (!mountpoint.equals(other.mountpoint)) {
            return false;
        }
        return true;
    }

    private final String mountpoint;

    private FindOneWireAddresses(final String mountpoint)
    {
        this.mountpoint = mountpoint;
    }

    public String getMountpoint()
    {
        return mountpoint;
    }

    public static FindOneWireAddresses on(final String mountpoint)
    {
        return new FindOneWireAddresses(mountpoint);
    }
}
