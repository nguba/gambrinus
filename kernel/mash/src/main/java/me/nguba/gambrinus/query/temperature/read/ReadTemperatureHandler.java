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
package me.nguba.gambrinus.query.temperature.read;

import me.nguba.gambrinus.cqrs.query.QueryHandler;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselRepository;

import java.util.Optional;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ReadTemperatureHandler
        implements QueryHandler<ReadTemperature, ReadTemperatureResult>
{
    private final VesselRepository vessels;

    private ReadTemperatureHandler(final VesselRepository vessels)
    {
        this.vessels = vessels;
    }

    public static ReadTemperatureHandler on(final VesselRepository vessels)
    {
        return new ReadTemperatureHandler(vessels);
    }

    @Override
    public void validate(final ReadTemperature query, final Errors errors)
    {
        if (query.getVesselId() == null) {
            errors.add(Reason.from("No vesselId"));
        }
    }

    @Override
    public ReadTemperatureResult query(final ReadTemperature query)
    {
        final Optional<Vessel> vessel = vessels.read(query.getVesselId());
        return ReadTemperatureResult.of(vessel.get().processValue());
    }

}
