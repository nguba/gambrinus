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
package me.nguba.gambrinus.equipment;

import me.nguba.gambrinus.ddd.Aggregate;
import me.nguba.gambrinus.process.ProcessValue;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Tank extends Aggregate<TankId>
{
    public static Tank with(final TankId id)
    {
        return new Tank(id);
    }

    private final ConcurrentMap<ProbeId, Probe> probes = new ConcurrentHashMap<>();

    private Tank(final TankId id)
    {
        super(id);
    }

    /**
     * Configures a probe for usage within this tank.
     *
     * @param id
     * @param probe
     */
    public void add(final ProbeId id, final Probe probe)
    {
        probes.put(id, probe);
    }

    /**
     * Returns the {@link ProcessValue} of the requested probe.
     *
     * @param probeId
     * @return the temperature measured
     * @throws ProbeNotConfigured
     */
    public ProcessValue read(final ProbeId probeId) throws ProbeNotConfigured
    {
        final Optional<Probe> probe = Optional.ofNullable(probes.get(probeId));
        return probe.orElseThrow(() -> ProbeNotConfigured.on(probeId)).processValue();
    }

}
