package me.nguba.gambrinus.owfs;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

import me.nguba.gambrinus.ddd.Aggregate;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.process.Temperature;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class OwfsSensor extends Aggregate<OneWireAddress>
{
  private final Path     latesttemp;
  private final OwfsRoot root;
  private final Path     path;

  private OwfsSensor(final OwfsRoot root, final OneWireAddress address)
  {
    super(address);
    this.root = root;

    path = Paths.get(root.getValue().getPath(), address.toString());

    latesttemp = Paths.get(path.toString(), "latesttemp");
  }

  public static final OwfsSensor from(final OwfsRoot root, final OneWireAddress address)
  {
    return new OwfsSensor(root, address);
  }

  public Optional<Temperature> read() throws IOException
  {
    try (final FileChannel channel = FileChannel.open(latesttemp, StandardOpenOption.READ)) {
      final ByteBuffer buf = ByteBuffer.allocate(8);
      final StringBuilder builder = new StringBuilder();
      while (channel.read(buf) != -1) {
        buf.flip();
        while (buf.hasRemaining())
          builder.append((char) buf.get());
        buf.clear();
        return Optional.of(Temperature.celsius(Double.parseDouble(builder.toString())));
      }
    }
    return Optional.empty();
  }

  @Override
  public String toString()
  {
    return root.toString();
  }

  public boolean isValid()
  {
    return latesttemp.toFile().exists();
  }

  public Path getPath()
  {
    return latesttemp;
  }
}
