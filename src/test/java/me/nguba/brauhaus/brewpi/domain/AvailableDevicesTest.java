package me.nguba.brauhaus.brewpi.domain;

import me.nguba.brauhaus.TestUtils;
import me.nguba.brauhaus.brewpi.BrewpiMapper;
import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class AvailableDevicesTest {

  private final BrewpiMapper mapper = new BrewpiMapper();

  @Test
  void equalsContract() {
    EqualsVerifier.forClass(AvailableDevices.class).verify();
  }

  @Test
  void canSerialiseToJson() throws Exception {

    final String json = mapper.toJson(BrewPiMother.availableDevices());

    final String expected = TestUtils.readFile("json/availableDevices.json");

    assertThat(json).asString().isEqualTo(expected);
  }

  @Test
  void toStringContainsCollection() {
    assertThat(BrewPiMother.availableDevices().toString()).containsOnlyOnce("h=");
  }
}
