package me.nguba.gambrinus.command.vessel.create;

import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.onewire.OneWireAddress;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

class CreateVesselMutatorTest
{
    private final VesselRepository repo = new VesselRepository();

    private CreateVesselMutator mutator;

    private final Errors errors = Errors.empty();

    private final VesselId vesselId = VesselId.of("a");

    private CreateVessel command;

    @BeforeEach
    void setUp()
    {
        mutator = new CreateVesselMutator(repo);

        command = CreateVessel.from(vesselId,
                                    OneWireAddress.of("28.273B5D070000"));
    }

    @Test
    void createsEntryInRepository()
    {
        mutator.mutate(command);

        final Optional<Vessel> actual = repo.read(vesselId);
        assertThat(actual.isPresent()).isTrue();
    }

    @Test
    void onCompletion()
    {
        assertThat(mutator.onCompletion(command)).isEqualTo(VesselCreated.from(vesselId));
    }

    @Test
    void noVesselId()
    {
        mutator.validate(CreateVessel.from(null, null), errors);

        final ValidationFailed failed = verify();

        assertThat(failed.getErrors().has(Reason.from("VesselId cannot be null.")));
    }

    @Test
    void alreadyExists()
    {
        repo.create(Vessel.of(vesselId));

        mutator.validate(command, errors);

        final ValidationFailed failed = verify();

        assertThat(failed.getErrors().has(Reason
                .from(String.format("Vessel already configured.", command.getVesselId()))))
                        .isTrue();
    }

    @Test
    void noAddress()
    {
        mutator.validate(CreateVessel.from(null, null), errors);

        final ValidationFailed failed = verify();

        assertThat(failed.getErrors().has(Reason.from("OneWireAddress cannot be null.")));
    }

    private ValidationFailed verify()
    {
        final ValidationFailed failed = assertThrows(ValidationFailed.class, () -> errors.verify());
        return failed;
    }

}
