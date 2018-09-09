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
package me.nguba.gambrinus.cqrs.handler;

import java.util.Optional;

import me.nguba.gambrinus.cqrs.query.Result;
import me.nguba.gambrinus.equipment.Vessel;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 *
 */
public final class FindVesselResult implements Result<Vessel>
{
    public static FindVesselResult empty()
    {
        return new FindVesselResult(Optional.empty());
    }

    public static FindVesselResult from(final Vessel vessel)
    {
        return new FindVesselResult(Optional.of(vessel));
    }

    public static FindVesselResult of(final Optional<Vessel> read)
    {
        return new FindVesselResult(read);
    }

    private final Optional<Vessel> vessel;

    public FindVesselResult(final Optional<Vessel> vessel)
    {
        this.vessel = vessel;
    }

    @Override
    public Optional<Vessel> getResult()
    {
        return vessel;
    }
}
