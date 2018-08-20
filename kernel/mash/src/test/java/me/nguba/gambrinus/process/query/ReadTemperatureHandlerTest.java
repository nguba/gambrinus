package me.nguba.gambrinus.process.query;

import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.process.Temperature;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ReadTemperatureHandlerTest
{
    private ReadTemperatureHandler handler;

    private final VesselRepository vessels = new VesselRepository();

    @BeforeEach
    void setUp()
    {
        handler = new ReadTemperatureHandler(vessels);
    }

    @Test
    void validation()
    {
        final Errors results = Errors.empty();

        handler.validate(ReadTemperature.from(null), results);

        final ValidationFailed failed = assertThrows(ValidationFailed.class,
                                                     () -> results.verify());

        assertThat(failed.getErrors().has(Reason.from("No vesselId")));
    }

    @Test
    void temperatureResult()
    {
        final VesselId vesselId = VesselId.of("boil");
        vessels.create(Vessel.inactive(vesselId));

        final ReadTemperatureResult result = handler.run(ReadTemperature.from(vesselId));

        assertThat(result.getResult().get()).isEqualTo(Temperature.celsius(0));
    }

}
