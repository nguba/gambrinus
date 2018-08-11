package me.nguba.gambrinus.owfs;

import java.io.File;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class OwfsMount extends OwfsFile
{
    private OwfsMount(final File value)
    {
        super(value);
    }

    public static OwfsMount from(final OwfsRoot root, final OwfsAddress address)
    {
        return new OwfsMount(new File(root.getValue(), address.getValue()));
    }

    @Override
    public boolean isValid()
    {
        return getValue().isDirectory();
    }
}
