package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.ddd.support.SingleValueObject;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class OwfsAddress extends SingleValueObject<String>
{
    private OwfsAddress(final String value)
    {
        super(value);
    }

    public static OwfsAddress of(final String address)
    {
        return new OwfsAddress(address);
    }

    public boolean isValid()
    {
        if (super.isValid() && !getValue().isEmpty() && getValue().length() == 15) {
            return getValue().startsWith("28.");
        }
        return false;
    }
}
