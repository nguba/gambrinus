/*
    Copyright (C) 2018  Nicolai P. Guba

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.ddd.Aggregate;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.process.Temperature;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

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
        if (!isValid()) {
            throw new FileNotFoundException("sensor is not mapped to an address");
        }
        try (final FileChannel channel = FileChannel.open(latesttemp, StandardOpenOption.READ)) {
            final ByteBuffer buf = ByteBuffer.allocate(8);
            final StringBuilder builder = new StringBuilder();
            while (channel.read(buf) != -1) {
                buf.flip();
                while (buf.hasRemaining()) {
                    builder.append((char) buf.get());
                }
                buf.clear();
                return Optional.of(Temperature.celsius(Double.parseDouble(builder.toString())));
            }
        }
        return Optional.empty();
    }

    @Override
    public String toString()
    {
        return root.getValue().getAbsolutePath();
    }

    public boolean isValid()
    {
        return latesttemp.toFile().exists();
    }
}
