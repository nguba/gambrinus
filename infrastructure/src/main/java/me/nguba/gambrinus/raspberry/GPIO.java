package me.nguba.gambrinus.raspberry;

import me.nguba.gambrinus.ddd.support.SingleValueObject;

import com.pi4j.io.gpio.Pin;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class GPIO extends SingleValueObject<Pin>
{
    private GPIO(Pin value)
    {
        super(value);
    }

    public static GPIO from(Pin pin)
    {
        return new GPIO(pin);
    }
}
