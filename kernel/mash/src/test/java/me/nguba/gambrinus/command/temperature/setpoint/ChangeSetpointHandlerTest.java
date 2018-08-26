package me.nguba.gambrinus.command.temperature.setpoint;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.process.Temperature;

class ChangeSetpointHandlerTest
{
  private final VesselRepository repo     = new VesselRepository();
  private ChangeSetpointHandler  mutator;
  private final VesselId         id       = VesselId.of("mash");
  private final Temperature      setpoint = Temperature.celsius(68);

  @BeforeEach
  void setUp()
  {
    mutator = ChangeSetpointHandler.from(repo);
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
    assertThrows(IllegalArgumentException.class, () -> mutator.changeStateFor(null));
  }

  @Test
  void validation() throws Exception
  {
    final Errors results = Errors.empty();

    mutator.validate(ChangeSetpoint.on(null, null), results);

    final ValidationFailed exception = assertThrows(ValidationFailed.class, () -> results.verify());

    assertThat(exception.getErrors().has(Reason.from("No vesselId"))).isTrue();
    assertThat(exception.getErrors().has(Reason.from("No setpoint"))).isTrue();
  }
}