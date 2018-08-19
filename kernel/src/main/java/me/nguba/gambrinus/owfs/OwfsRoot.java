package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.onewire.OneWireAddress;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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

    public OwfsSensor[] listSensors() throws IOException
    {
        final Set<OwfsSensor> sensors = new HashSet<>();

        for (final File file : getValue().listFiles((file) -> {
            return (file.isDirectory() && file.canRead() && file.getName().startsWith("28."));
        })) {
            sensors.add(OwfsSensor.mount(this, OneWireAddress.of(file.getName())));
        }
        return sensors.toArray(new OwfsSensor[sensors.size()]);
    }
}
