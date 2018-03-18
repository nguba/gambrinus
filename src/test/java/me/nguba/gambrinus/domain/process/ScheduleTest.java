package me.nguba.gambrinus.domain.process;

import me.nguba.gambrinus.domain.process.exception.DuplicateStep;
import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class ScheduleTest {

  final List<Step> expected = new ArrayList<Step>(3);

  private Schedule schedule;

  private final UUID uuid = UUID.randomUUID();

  @Test
  void cannotAddDuplicateStep() throws Exception {
    fillSchedule();

    assertThrows(DuplicateStep.class, () -> {
      schedule.add(ProcessMother.betaAmylase());
    });
  }

  private void ensureInsertionOrderIsTheSame() {
    assertThat(schedule.steps()).containsExactlyElementsOf(expected);
  }

  private void ensureStepsInScheduleHaventChanged() {
    assertThat(schedule.steps()).isEqualTo(expected);
  }

  private void fillSchedule() throws Exception {
    for (final Step step : expected) {
      schedule.add(step);
    }
  }

  @Test
  void hashCodeEqualsContract() {
    EqualsVerifier.forClass(Schedule.class).withIgnoredFields("name", "steps").verify();
  }

  private void removeStepsFromReturnedList() {
    final List<Step> steps = schedule.steps();
    steps.clear();
  }

  @BeforeEach
  void setUp() {
    schedule = Schedule.make(uuid, "Bayrisch Hell");

    expected.add(ProcessMother.doughIn());
    expected.add(ProcessMother.betaAmylase());
    expected.add(ProcessMother.alphaAmylase());
  }

  @Test
  void stepsAreReturnedAsCopy() throws Exception {
    fillSchedule();

    removeStepsFromReturnedList();

    ensureStepsInScheduleHaventChanged();
  }

  @Test
  void stepsMaintainInsertionOrder() throws Exception {

    fillSchedule();

    ensureInsertionOrderIsTheSame();
  }

  @Test
  void toStringContainsImportantFields() {
    assertThat(schedule.toString()).contains("id=", "name=", "steps=");
  }

  @Test
  void identityIsUUID() {
    assertThat(schedule.id()).isEqualTo(uuid);
  }
}
