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

import java.util.HashSet;
import java.util.Set;

public class AdminQueries
{
    private final VesselRepository vessels;

    public AdminQueries(final VesselRepository vessels)
    {
        this.vessels = vessels;
    }

    public Set<Vessel> run(FindVessels query) throws ValidationFailed
    {
        return QueryProcessor.query(query, FindVesselsHandler.on(vessels)).getResult()
                .orElse(new HashSet<Vessel>());
    }

    public Set<OneWireAddress> run(FindOneWireAddresses query) throws ValidationFailed
    {
        return QueryProcessor.query(query, FindOneWireAddressesHandler.on()).getResult().get();
    }
}
