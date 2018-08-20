package me.nguba.gambrinus;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
@ConfigurationProperties(prefix = "gambrinus")
public class GambrinusOptions
{
    private String mountpoint;

    public String getMountpoint()
    {
        return mountpoint;
    }

    public void setMountpoint(final String mountpoint)
    {
        this.mountpoint = mountpoint;
    }

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
        final GambrinusOptions other = (GambrinusOptions) obj;
        if (mountpoint == null) {
            if (other.mountpoint != null) {
                return false;
            }
        } else if (!mountpoint.equals(other.mountpoint)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("GambrinusOptions [mountpoint=").append(mountpoint).append("]");
        return builder.toString();
    }

}
