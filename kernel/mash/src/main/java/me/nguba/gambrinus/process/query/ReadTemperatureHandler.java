package me.nguba.gambrinus.process.query;

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
