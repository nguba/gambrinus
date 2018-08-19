package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.ddd.support.SingleValueObject;

import java.io.File;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public abstract class OwfsDirectory extends SingleValueObject<File>
{
    protected OwfsDirectory(final File value)
    {
        super(value);
    }

    @Override
    public boolean isValid()
    {
        return getValue().isDirectory();
    }
}
