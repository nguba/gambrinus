package me.nguba.gambrinus.query.vessel;

import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FindVesselsHandlerTest
{
    private final VesselRepository repository = new VesselRepository();

    private FindVesselsHandler handler;

    @BeforeEach
    void setup()
    {
        handler = new FindVesselsHandler(repository);
    }

    @Test
    void validation()
    {
        final Errors errors = Errors.empty();
        handler.validate(FindVessels.create(), errors);

        assertThat(errors.hasErrors()).isFalse();
    }

    @Test
    void noVesselsFound()
    {
        final FindVesselsResult result = handler.run(FindVessels.create());

        assertThat(result.getResult().get()).isEmpty();
    }

    @Test
    void returnVessels()
    {
        final Vessel[] expected = { Vessel.of(VesselId.of("a")), Vessel.of(VesselId.of("b")) };
        for (final Vessel v : expected) {
            repository.create(v);
        }

        final FindVesselsResult result = handler.run(FindVessels.create());

        assertThat(result.getResult().get()).contains(expected);
    }

}
