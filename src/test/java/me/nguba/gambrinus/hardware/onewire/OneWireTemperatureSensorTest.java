package me.nguba.gambrinus.hardware.onewire;

import me.nguba.gambrinus.hardware.HardwareMother;
import me.nguba.gambrinus.hardware.TemperatureSensor;
import me.nguba.gambrinus.hardware.onewire.OneWireAddress;
import me.nguba.gambrinus.hardware.onewire.OneWireSensor;
import me.nguba.gambrinus.hardware.onewire.OneWireTemperatureSensor;
import me.nguba.gambrinus.process.Event;
import me.nguba.gambrinus.process.Temperature;
import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OneWireTemperatureSensorTest {

  private final OneWireSensor delegate = HardwareMother.boilKettleSensor();

  private TemperatureSensor<OneWireAddress> sensor;

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
