package me.nguba.gambrinus.owfs;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import me.nguba.gambrinus.onewire.OneWireAddress;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class OwfsRoot extends OwfsDirectory
{
  private OwfsRoot(final File value)
  {
    super(value);
  }

  public static OwfsRoot of(final String path)
  {
    return new OwfsRoot(new File(path));
  }

  public OwfsSensor[] listSensors()
  {
    final Set<OwfsSensor> sensors = new HashSet<>();

    if (isValid())
      try {
        for (final File file : getValue().listFiles((file) -> {
          return file.isDirectory() && file.canRead()
              && file.getName().startsWith("28.");
        }))
          sensors.add(OwfsSensor.mount(this, OneWireAddress.of(file.getName())));
      } catch (final IOException e) {
        throw new RuntimeException(e);
      }
    return sensors.toArray(new OwfsSensor[sensors.size()]);
  }
}
