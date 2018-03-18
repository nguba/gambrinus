package me.nguba.gambrinus.domain.hardware;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.UUID;

class VesselTest {

  private final Vessel vessel = Vessel.vessel(UUID.randomUUID(), "My label", null);

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

    final Vessel vessel = Vessel.vessel(null, label, null);

    assertThat(vessel.label()).isEqualTo("a");

    label = "b";

    assertThat(vessel.label()).isEqualTo("a");
  }
  
  
}
