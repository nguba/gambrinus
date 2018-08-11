package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.ddd.support.SingleValueObject;

import java.io.File;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public abstract class OwfsFile extends SingleValueObject<File>
{
    protected OwfsFile(File value)
    {
        super(value);
    }
    
    @Override
    public boolean isValid()
    {
        return getValue().exists();
    }
}
