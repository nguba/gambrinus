package me.nguba.gambrinus.brewpi.domain;

import me.nguba.gambrinus.TestUtils;
import me.nguba.gambrinus.brewpi.BrewpiMapper;
import me.nguba.gambrinus.brewpi.domain.Device;
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

  private final BrewpiMapper mapper = new BrewpiMapper();

  @Test
  void equalsContract() {
    EqualsVerifier.forClass(Device.class).withOnlyTheseFields("a", "v").verify();
  }

  @Test
  void canSerialiseToJson() throws Exception {

    final String json = mapper.toJson(BrewPiMother.device());

    final String expected = TestUtils.readFile("json/device.json");

    assertThat(json).asString().isEqualTo(expected);
  }

  @Test
  void idReturnsOneWireAddress() {
    assertThat(BrewPiMother.device().id())
        .isEqualTo(OneWireAddress.valueOf(SensorAddress.BOIL_KETTLE.toString()));
  }
}
