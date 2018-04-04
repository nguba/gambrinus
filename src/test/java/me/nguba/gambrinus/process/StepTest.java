package me.nguba.gambrinus.process;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StepTest {

  private final Step step = ProcessMother.doughIn();

  @Test
  void equalsContract() {
    EqualsVerifier.forClass(Step.class).verify();
  }

  @Test
  void toStringContainsTemperature() {
    assertThat(step.toString()).containsOnlyOnce("58.0 (C)");
  }

  @Test
  void toStringContainsInterval() {
    assertThat(step.toString()).containsOnlyOnce("15");
  }
}
