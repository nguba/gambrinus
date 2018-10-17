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

import me.nguba.gambrinus.command.FindVessel;
import me.nguba.gambrinus.cqrs.query.QueryHandler;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselRepository;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 *
 */
public final class FindVesselHandler implements QueryHandler<FindVessel, Optional<Vessel>>
{
    public static FindVesselHandler on(final VesselRepository vessels)
    {
        return new FindVesselHandler(vessels);
    }

    private final VesselRepository vessels;

    public FindVesselHandler(final VesselRepository vessels)
    {
        this.vessels = vessels;
    }

    @Override
    public Optional<Vessel> query(final FindVessel query)
    {
        return vessels.read(query.getId());
    }

    @Override
    public void validate(final FindVessel query, final Errors errors)
    {
        if (query.getId() == null)
            errors.add(Reason.from("vesselId cannot be null"));
    }
}
