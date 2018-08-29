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

import me.nguba.gambrinus.cqrs.query.QueryProcessor;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.query.onewire.FindOneWireAddresses;
import me.nguba.gambrinus.query.onewire.FindOneWireAddressesHandler;
import me.nguba.gambrinus.query.vessel.FindVessels;
import me.nguba.gambrinus.query.vessel.FindVesselsHandler;
import me.nguba.gambrinus.query.vessel.find.FindVessel;
import me.nguba.gambrinus.query.vessel.find.FindVesselHandler;

import java.util.HashSet;
import java.util.Set;

public class AdminQueries
{
    private final VesselRepository vessels;

    public AdminQueries(final VesselRepository vessels)
    {
        this.vessels = vessels;
    }

    public Set<Vessel> run(final FindVessels query) throws ValidationFailed
    {
        return QueryProcessor.query(query, FindVesselsHandler.on(vessels)).getResult()
                .orElse(new HashSet<Vessel>());
    }

    public Set<OneWireAddress> run(final FindOneWireAddresses query) throws ValidationFailed
    {
        return QueryProcessor.query(query, FindOneWireAddressesHandler.on()).getResult().get();
    }

    public Vessel run(final FindVessel query) throws ValidationFailed
    {
        return QueryProcessor.query(query, FindVesselHandler.on(vessels)).getResult().get();
    }
}
