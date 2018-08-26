package me.nguba.gambrinus;

import java.util.HashSet;
import java.util.Set;

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
