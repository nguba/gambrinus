package me.nguba.gambrinus.hardware;

import me.nguba.gambrinus.hardware.onewire.OneWireAddress;
import me.nguba.gambrinus.process.Temperature;
import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

class VesselTest {

  private static final TemperatureSensor<OneWireAddress> SENSOR = HardwareMother
      .boilKettleTemperatureSensor();

  private static final UUID ID = UUID.randomUUID();

  private final Vessel vessel = Vessel.make(ID, "My label", SENSOR);

  @Test
  void testEqualityContract() {
    EqualsVerifier.forClass(Vessel.class).withIgnoredFields("label", "sensor").verify();
  }

  @Test
  void toStringReturnsLabel() {
    assertThat(vessel.toString()).isEqualTo("My label");
  }

  @Test
  void labelIsImmutable() {
    String label = "a";

    final Vessel vessel = Vessel.make(ID, label, SENSOR);

    assertThat(vessel.label()).isEqualTo("a");

    label = "b";

    assertThat(vessel.label()).isEqualTo("a");
  }

  @Test
  void identityIsUUID() {
    assertThat(vessel.id()).isEqualTo(ID);
  }

  @Test
  void canReadTemperature() {
    assertThat(vessel.read()).isEqualTo(Temperature.valueOf(0));

  }

  @Test
  void idCannotBeNull() {
    final Exception thrown = assertThrows(IllegalArgumentException.class,
                                          () -> Vessel.make(null, "label", SENSOR));
    assertThat(thrown.getMessage()).isEqualTo("Identity cannot be null");
  }

  @Test
  void labelCannotBeNull() {
    final Exception thrown = assertThrows(IllegalArgumentException.class,
                                          () -> Vessel.make(ID, null, SENSOR));
    assertThat(thrown.getMessage()).isEqualTo("Label cannot be null");
  }

  @Test
  void sensorCannotBeNull() {
    final Exception thrown = assertThrows(IllegalArgumentException.class,
                                          () -> Vessel.make(ID, "label", null));
    assertThat(thrown.getMessage()).isEqualTo("Temperature Sensor cannot be null");
  }
}
