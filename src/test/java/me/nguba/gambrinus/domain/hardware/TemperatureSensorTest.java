package me.nguba.gambrinus.domain.hardware;

import me.nguba.gambrinus.domain.process.Event;
import me.nguba.gambrinus.domain.process.Temperature;
import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TemperatureSensorTest {

  private static final OneWireAddress ADDRESS = OneWireAddress
      .valueOf(SensorAddress.BOIL_KETTLE.toString());

  private final Sensor<OneWireAddress, Temperature> delegate = OneWireSensor.sensor(ADDRESS);

  private TemperatureSensor sensor;

  @BeforeEach
  void beforeEach() {
    sensor = TemperatureSensor.sensor(delegate);

  }

  @Test
  void equalityContract() {
    EqualsVerifier.forClass(TemperatureSensor.class).withOnlyTheseFields("id").verify();
  }

  @Test
  void identityIsAddress() {
    assertThat(sensor.id()).isEqualTo(ADDRESS);
  }

  @Test
  void readsThroughDelegate() {
    assertThat(sensor.read()).isEqualTo(Temperature.valueOf(0));

    delegate.update(Temperature.valueOf(65.0));

    assertThat(sensor.read()).isEqualTo(Temperature.valueOf(65.0));
  }

  @Test
  void onEventUpdatesTemperature() {
    assertThat(sensor.read()).isEqualTo(Temperature.valueOf(0));

    sensor.onEvent(Event.valueOf(Temperature.valueOf(65.0)));

    assertThat(sensor.read()).isEqualTo(Temperature.valueOf(65.0));
  }

  @Test
  void toStringHasMeaningfulFields() {
    assertThat(sensor.toString()).contains("id=", "delegate=");
  }
}
