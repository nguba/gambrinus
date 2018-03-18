package me.nguba.gambrinus.domain.hardware.onewire;

import me.nguba.gambrinus.domain.hardware.Sensor;
import me.nguba.gambrinus.domain.process.Degree;
import me.nguba.gambrinus.domain.process.Temperature;
import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class OneWireSensorTest {

  private static final OneWireAddress ADDRESS = OneWireAddress.valueOf("2851B75D07000026");

  private Sensor<OneWireAddress, Temperature> sensor;

  @BeforeEach
  public void setUp() {
    sensor = OneWireSensor.make(ADDRESS);
  }

  @Test
  void equalsContract() {
    EqualsVerifier.forClass(OneWireSensor.class).withIgnoredFields("temperature").verify();
  }

  @Test
  void canUpdateTemperature() {
    sensor.update(Temperature.valueOf(65.5));

    assertThat(sensor.read()).isEqualTo(Temperature.valueOf(65.5));
  }

  @Test
  void defaultTemperature() {
    assertThat(sensor.read()).isEqualTo(Temperature.valueOf(0, Degree.CELSIUS));
  }

  @Test
  void idReturnsAddress() {
    assertThat(sensor.id()).isEqualTo(ADDRESS);
  }

  @Test
  void toStringReturnsMeaningfulFields() {
    assertThat(sensor.toString()).containsOnlyOnce("id=").containsOnlyOnce("temperature=");
  }
}
