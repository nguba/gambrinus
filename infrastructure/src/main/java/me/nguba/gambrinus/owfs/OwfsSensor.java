package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.ddd.Aggregate;
import me.nguba.gambrinus.process.Temperature;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class OwfsSensor extends Aggregate<OwfsAddress>
{
    private final OwfsMount mount;
    private final Path      latesttemp;

    private OwfsSensor(final OwfsMount mount, final OwfsAddress address) throws IOException
    {
        super(address);
        this.mount = mount;
        latesttemp = Paths.get(mount.getValue().getPath(), "latesttemp");
    }

    public static OwfsSensor mount(final OwfsRoot root, final OwfsAddress address)
            throws IOException
    {
        final OwfsMount mount = OwfsMount.from(root, address);
        if (!mount.isValid()) {
            throw new IOException("Mountpoint does not exist: " + mount.getValue());
        }
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
            while ((channel.read(buf)) != -1) {
                buf.flip();
                while (buf.hasRemaining()) {
                    builder.append((char) buf.get());
                }
                buf.clear();
                return Temperature.celsius(Double.parseDouble(builder.toString()));
            }
        }
        return null;
    }
}
