package me.nguba.gambrinus.raspberry;

import com.pi4j.io.gpio.Pin;

import me.nguba.gambrinus.ddd.support.SingleValueObject;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class GPIO extends SingleValueObject<Pin>
{
  private GPIO(final Pin value)
  {
    super(value);
  }

  public static GPIO from(final Pin pin)
  {
    return new GPIO(pin);
  }
}
