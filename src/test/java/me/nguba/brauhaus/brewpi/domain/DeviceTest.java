package me.nguba.brauhaus.brewpi.domain;

import me.nguba.brauhaus.TestUtils;
import me.nguba.brauhaus.brewpi.BrewpiMapper;
import me.nguba.brauhaus.domain.hardware.OneWireAddress;
import me.nguba.brauhaus.domain.hardware.SensorAddress;
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
