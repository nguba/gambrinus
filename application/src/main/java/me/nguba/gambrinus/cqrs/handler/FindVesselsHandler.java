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

import me.nguba.gambrinus.command.FindVessels;
import me.nguba.gambrinus.cqrs.query.QueryHandler;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class FindVesselsHandler implements QueryHandler<FindVessels, Set<Vessel>>
{
    public static FindVesselsHandler on(final VesselRepository repository)
    {
        return new FindVesselsHandler(repository);
    }

    private final VesselRepository repository;

    private FindVesselsHandler(final VesselRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public Set<Vessel> query(final FindVessels query)
    {
        HashSet<Vessel> result = new HashSet<>();
        for(Vessel vessel : repository.findAll()) {
            result.add(vessel);
        }
        return result;
    }

    @Override
    public void validate(final FindVessels query, final Errors errors)
    {
    }
}
