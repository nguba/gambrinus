package me.nguba.gambrinus.owfs;

import java.io.File;

import me.nguba.gambrinus.onewire.OneWireAddress;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class OwfsMount extends OwfsDirectory
{
  private OwfsMount(final File value)
  {
    super(value);
  }

  public static OwfsMount from(final OwfsRoot root, final OneWireAddress address)
  {
    return new OwfsMount(new File(root.getValue(), address.getValue()));
  }

  @Override
  public boolean isValid()
  {
    return getValue().isDirectory();
  }
}
