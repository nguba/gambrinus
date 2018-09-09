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

import java.io.File;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import me.nguba.gambrinus.onewire.OneWireAddress;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class OwfsRoot extends OwfsDirectory
{
    public static OwfsRoot of(final String path)
    {
        return new OwfsRoot(new File(path));
    }

    public static OwfsRoot test()
    {
        return OwfsRoot.of("src/test/resources/owfs");
    }

    private OwfsRoot(final File value)
    {
        super(value);
    }

    public OwfsSensor[] listSensors()
    {
        final Set<OwfsSensor> sensors = new HashSet<>();

        if (isValid())
            for (final File file : getValue().listFiles((file) -> {
                return file.isDirectory() && file.canRead()
                        && file.getName().startsWith("28.");
            }))
                sensors.add(OwfsSensor.from(this, OneWireAddress.of(file.getName())));

        return sensors.toArray(new OwfsSensor[sensors.size()]);
    }

    public static OwfsRoot of(Path root)
    {
        return new OwfsRoot(root.toFile());
    }
}
