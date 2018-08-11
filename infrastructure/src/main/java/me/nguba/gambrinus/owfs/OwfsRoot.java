package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.ddd.support.SingleValueObject;

import java.io.File;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class OwfsRoot extends SingleValueObject<File>
{
    protected OwfsRoot(File value)
    {
        super(value);
    }

    public static OwfsRoot of(String path)
    {
        return new OwfsRoot(new File(path));
    }

    @Override
    public boolean isValid()
    {
        return getValue().exists();
    }
}
