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
package me.nguba.gambrinus;

import me.nguba.gambrinus.command.ReadTemperature;
import me.nguba.gambrinus.cqrs.query.QueryProcessor;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.process.Temperature;
import me.nguba.gambrinus.query.temperature.read.ReadTemperatureHandler;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class BrewQueries
{
    private final VesselRepository vessels;

    public BrewQueries(final VesselRepository vessels)
    {
        this.vessels = vessels;
    }

    public Temperature run(final ReadTemperature query) throws ValidationFailed
    {
        return QueryProcessor.query(query, ReadTemperatureHandler.on(vessels)).getResult()
                .orElse(Temperature.celsius(0));
    }
}
