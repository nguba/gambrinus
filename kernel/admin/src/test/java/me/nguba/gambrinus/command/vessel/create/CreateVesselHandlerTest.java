package me.nguba.gambrinus.command.vessel.create;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.owfs.OwfsRoot;

class CreateVesselHandlerTest
{
  private final VesselRepository repo = new VesselRepository();

  private CreateVesselHandler mutator;

  private final Errors errors = Errors.empty();

  private final VesselId vesselId = VesselId.of("a");

  private CreateVessel command;

  @BeforeEach
  void setUp()
  {
    mutator = new CreateVesselHandler(repo);

    command = CreateVessel.from(vesselId,
                                OwfsRoot.of("src/test/resources/owfs"),
                                OneWireAddress.of("28.273B5D070000"));
  }

  @Test
  void createsEntryInRepository()
  {
    mutator.changeStateFor(command);

    final Optional<Vessel> actual = repo.read(vesselId);
    assertThat(actual.isPresent()).isTrue();
  }

  @Test
  void noVesselId()
  {
    mutator.validate(CreateVessel.from(null, null, null), errors);

    final ValidationFailed failed = verify();

    assertThat(failed.getErrors().has(Reason.from("VesselId cannot be null"))).isTrue();
  }

  @Test
  void alreadyExists()
  {
    repo.create(Vessel.inactive(vesselId));

    mutator.validate(command, errors);

    final ValidationFailed failed = verify();

    assertThat(failed.getErrors().has(Reason
        .from(String.format("Vessel already configured", command.getVesselId()))))
            .isTrue();
  }

  @Test
  void noAddress()
  {
    mutator.validate(CreateVessel.from(null, null, null), errors);

    final ValidationFailed failed = verify();

    assertThat(failed.getErrors().has(Reason.from("OneWireAddress cannot be null"))).isTrue();
  }

  @Test
  void noRoot()
  {
    mutator.validate(CreateVessel.from(null, null, null), errors);

    final ValidationFailed failed = verify();

    assertThat(failed.getErrors().has(Reason.from("OwfsRoot cannot be null"))).isTrue();
  }

  @Test
  void cannotMountPath()
  {
    final OwfsRoot root = OwfsRoot.of("non/existing/path");
    final OneWireAddress address = OneWireAddress.of("28.273B5D070000");

    mutator.validate(CreateVessel.from(VesselId.of("arse"), root, address), errors);
    final ValidationFailed failed = verify();

    assertThat(failed.getErrors().toString())
        .contains("Invalid sensor: non/existing/path/28.273B5D070000/latesttemp");
  }

  private ValidationFailed verify()
  {
    final ValidationFailed failed = assertThrows(ValidationFailed.class, () -> errors.verify());
    return failed;
  }

}