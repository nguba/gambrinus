package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.onewire.OneWireAddress;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class OwfsMother
{
  private OwfsMother()
  {
    super();
  }

  public static OwfsRoot root()
  {
    return OwfsRoot.of("src/test/resources/owfs");
  }

  public static OneWireAddress address()
  {
    return OneWireAddress.of("28.4BBB68080000");
  }
}
