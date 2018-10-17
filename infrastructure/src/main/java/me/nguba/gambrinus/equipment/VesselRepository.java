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

import me.nguba.gambrinus.ddd.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class VesselRepository implements Repository<VesselId, Vessel>
{
    private final ConcurrentMap<VesselId, Vessel> store = new ConcurrentHashMap<>();

    @Override
    public Optional<VesselId> create(final Vessel vessel)
    {
        if (vessel == null)
            return Optional.empty();

        store.put(vessel.getId(), vessel);
        return Optional.of(vessel.getId());
    }

    @Override
    public void delete(final VesselId identifier)
    {
        if (identifier == null)
            return;
        store.remove(identifier);
    }

    public Vessel[] findAll()
    {
        return store.values().toArray(new Vessel[store.size()]);
    }

    @Override
    public Optional<Vessel> read(final VesselId identifier)
    {
        if (identifier == null)
            return Optional.empty();

        return Optional.ofNullable(store.get(identifier));
    }

    @Override
    public void update(final Vessel aggregate)
    {
        throw new UnsupportedOperationException("Vessels cannot be updated");
    }
}
