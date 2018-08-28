package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.query.QueryProcessor;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.process.Temperature;
import me.nguba.gambrinus.query.temperature.read.ReadTemperature;
import me.nguba.gambrinus.query.temperature.read.ReadTemperatureHandler;

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
