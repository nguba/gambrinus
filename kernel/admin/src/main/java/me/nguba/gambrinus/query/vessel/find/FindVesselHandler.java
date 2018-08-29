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
package me.nguba.gambrinus.query.vessel.find;

import me.nguba.gambrinus.cqrs.query.QueryHandler;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselRepository;

import java.util.Optional;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 *
 */
public final class FindVesselHandler implements QueryHandler<FindVessel, FindVesselResult>
{
    private final VesselRepository vessels;

    public FindVesselHandler(final VesselRepository vessels)
    {
        this.vessels = vessels;
    }

    @Override
    public void validate(final FindVessel query, final Errors errors)
    {
        if (query.getId() == null) {
            errors.add(Reason.from("vesselId cannot be null"));
        }
    }

    @Override
    public FindVesselResult query(final FindVessel query)
    {
        final Optional<Vessel> read = vessels.read(query.getId());
        return FindVesselResult.of(read);
    }

    public static FindVesselHandler on(final VesselRepository vessels)
    {
        return new FindVesselHandler(vessels);
    }
}
