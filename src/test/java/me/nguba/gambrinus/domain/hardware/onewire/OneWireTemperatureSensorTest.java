package me.nguba.gambrinus.domain.hardware.onewire;

import me.nguba.gambrinus.domain.hardware.HardwareMother;
import me.nguba.gambrinus.domain.hardware.TemperatureSensor;
import me.nguba.gambrinus.domain.process.Event;
import me.nguba.gambrinus.domain.process.Temperature;
import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OneWireTemperatureSensorTest {

  private final OneWireSensor delegate = HardwareMother.boilKettleSensor();

  private TemperatureSensor<OneWireAddress, Temperature> sensor;

  @BeforeEach
  void beforeEach() {
    sensor = OneWireTemperatureSensor.make(delegate);

  }

  @Test
  void equalityContract() {
    EqualsVerifier.forClass(OneWireTemperatureSensor.class).withOnlyTheseFields("id").verify();
  }

  @Test
  void identityIsAddress() {
    assertThat(sensor.id()).isEqualTo(delegate.id());
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