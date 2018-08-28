/**
 *
 */
package me.nguba.gambrinus.query.vessel.find;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 *
 */
class FindVesselHandlerTest
{
  private final VesselRepository vessels = new VesselRepository();

  private FindVesselHandler handler;

  @BeforeEach
  void setUp()
  {
    handler = FindVesselHandler.on(vessels);
  }

  @Test
  void validation()
  {
    final Errors errors = Errors.empty();

    handler.validate(FindVessel.of(null), errors);

    final ValidationFailed failed = assertThrows(ValidationFailed.class, () -> errors.verify());
    assertThat(failed.getErrors().toString()).contains("vesselId cannot be null");
  }

  @Test
  void resultNotFound()
  {
    final FindVesselResult result = handler.query(FindVessel.of(VesselId.of("HLT")));
    assertThat(result.getResult().isPresent()).isFalse();
  }

  @Test
  void result()
  {
    final Vessel expected = Vessel.inactive(VesselId.of("HLT"));
    vessels.create(expected);

    final FindVesselResult result = handler.query(FindVessel.of(VesselId.of("HLT")));
    assertThat(result.getResult().get()).isEqualTo(expected);
  }

}
