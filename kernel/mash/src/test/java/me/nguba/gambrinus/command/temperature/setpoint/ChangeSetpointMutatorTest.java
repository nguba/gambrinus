package me.nguba.gambrinus.command.temperature.setpoint;

import me.nguba.gambrinus.command.temperature.setpoint.ChangeSetpoint;
import me.nguba.gambrinus.command.temperature.setpoint.ChangeSetpointMutator;
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

class ChangeSetpointMutatorTest
{
    private final VesselRepository repo     = new VesselRepository();
    private ChangeSetpointMutator  mutator;
    private final VesselId         id       = VesselId.of("mash");
    private final Temperature      setpoint = Temperature.celsius(68);

    @BeforeEach
    void setUp()
    {
        mutator = ChangeSetpointMutator.from(repo);
    }

    @Test
    void mutateNonExistingVessel()
    {
        assertThrows(IllegalArgumentException.class,
                     () -> mutator.changeStateFor(ChangeSetpoint.on(id, setpoint)));
    }

    @Test
    void mutateMofifiesSetpoint()
    {
        repo.create(Vessel.inactive(id));

        mutator.changeStateFor(ChangeSetpoint.on(id, setpoint));

        assertThat(repo.read(id).get().setpoint()).isEqualTo(Temperature.celsius(68));
    }

    @Test
    void mutateNullCommand()
    {
        mutator.changeStateFor(null);
    }

    @Test
    void validation() throws Exception
    {
        final Errors results = Errors.empty();

        mutator.validate(ChangeSetpoint.on(null, null), results);

        final ValidationFailed exception = assertThrows(ValidationFailed.class,
                                                        () -> results.verify());

        assertThat(exception.getErrors().has(Reason.from("No vesselId"))).isTrue();
        assertThat(exception.getErrors().has(Reason.from("No setpoint"))).isTrue();
    }
}
