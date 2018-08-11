package me.nguba.gambrinus.owfs;

import java.io.File;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class OwfsRoot extends OwfsFile
{
    private OwfsRoot(final File value)
    {
        super(value);
    }

    public static OwfsRoot of(final String path)
    {
        return new OwfsRoot(new File(path));
    }
}
