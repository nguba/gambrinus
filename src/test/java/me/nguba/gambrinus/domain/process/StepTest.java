package me.nguba.gambrinus.domain.process;

import me.nguba.gambrinus.domain.process.Step;
import me.nguba.gambrinus.domain.process.Temperature;
import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.time.Duration;

class StepTest {

  @Test
  void equalsContract() {
    EqualsVerifier.forClass(Step.class).verify();
  }

  @Test
  void toStringContainsTemperature() {
    final Step step = Step.valueOf(Temperature.valueOf(58.0), Duration.ofMinutes(15));
    assertThat(step.toString()).containsOnlyOnce("58.0 (C)");
  }

  @Test
  void toStringContainsInterval() {
    final Step step = Step.valueOf(Temperature.valueOf(58.0), Duration.ofMinutes(15));
    assertThat(step.toString()).containsOnlyOnce("15");
  }
}
