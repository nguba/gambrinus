package me.nguba.gambrinus.brewpi;

import me.nguba.gambrinus.TestUtils;
import me.nguba.gambrinus.brewpi.serialization.SparkSerializerService;
import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class AvailableDevicesTest {

  private final SparkSerializerService service = new SparkSerializerService();

  @Test
  void equalsContract() {
    EqualsVerifier.forClass(AvailableDevices.class).verify();
  }

  @Test
  void canSerialiseToJson() throws Exception {

    final String json = service.toJson(BrewPiMother.availableDevices());

    final String expected = TestUtils.readFile("json/availableDevices.json");

    assertThat(json).asString().isEqualTo(expected);
  }

  @Test
  void toStringContainsCollection() {
    assertThat(BrewPiMother.availableDevices().toString()).containsOnlyOnce("h=");
  }
}
