package me.nguba.gambrinus.owfs;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import me.nguba.gambrinus.ddd.Aggregate;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.process.Temperature;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class OwfsSensor extends Aggregate<OneWireAddress>
{
  private final OwfsMount mount;
  private final Path      latesttemp;

  private OwfsSensor(final OwfsMount mount, final OneWireAddress address) throws IOException
  {
    super(address);
    this.mount = mount;

    latesttemp = Paths.get(mount.getValue().getPath(), "latesttemp");
  }

  public static OwfsSensor mount(final OwfsRoot root, final OneWireAddress address)
      throws IOException
  {
    final OwfsMount mount = OwfsMount.from(root, address);
    if (!mount.isValid())
      throw new IOException("Mountpoint does not exist: " + mount.getValue());
    return new OwfsSensor(mount, address);
  }

  public OwfsMount getMount()
  {
    return mount;
  }

  public Temperature read() throws IOException
  {
    try (final FileChannel channel = FileChannel.open(latesttemp, StandardOpenOption.READ)) {
      final ByteBuffer buf = ByteBuffer.allocate(8);
      final StringBuilder builder = new StringBuilder();
      while (channel.read(buf) != -1) {
        buf.flip();
        while (buf.hasRemaining())
          builder.append((char) buf.get());
        buf.clear();
        return Temperature.celsius(Double.parseDouble(builder.toString()));
      }
    }
    return null;
  }

  @Override
  public String toString()
  {
    final StringBuilder builder = new StringBuilder();
    builder.append("OwfsSensor [mount=").append(mount).append(", latesttemp=")
        .append(latesttemp).append("]");
    return builder.toString();
  }
}
