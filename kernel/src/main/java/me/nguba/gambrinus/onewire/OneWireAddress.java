package me.nguba.gambrinus.onewire;

import me.nguba.gambrinus.ddd.support.SingleValueObject;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class OneWireAddress extends SingleValueObject<String>
{
    private OneWireAddress(final String value)
    {
        super(value);
    }

    public static OneWireAddress of(final String address)
    {
        return new OneWireAddress(address);
    }

    @Override
    public boolean isValid()
    {
        if (super.isValid() && !getValue().isEmpty() && getValue().length() == 15) {
            return getValue().startsWith("28.");
        }
        return false;
    }

    public static OneWireAddress empty()
    {
        return of("");
    }
}
