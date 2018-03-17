package me.nguba.gambrinus.brewpi.domain;

import me.nguba.gambrinus.brewpi.serialization.SparkSerializer;
import me.nguba.gambrinus.domain.hardware.OneWireAddress;
import me.nguba.gambrinus.domain.hardware.SensorAddress;
import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class DeviceTest {

  private final SparkSerializer mapper = new SparkSerializer();

  @Test
  void equalsContract() {
    EqualsVerifier.forClass(Device.class).withOnlyTheseFields("a", "v").verify();
  }

  @Test
  void idReturnsOneWireAddress() {
    assertThat(BrewPiMother.device().id())
        .isEqualTo(OneWireAddress.valueOf(SensorAddress.BOIL_KETTLE.toString()));
  }
}
