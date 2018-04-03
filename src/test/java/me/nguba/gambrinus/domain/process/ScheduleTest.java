package me.nguba.gambrinus.domain.process;

import nl.jqno.equalsverifier.EqualsVerifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class ScheduleTest {

  private static final Logger LOG = LoggerFactory.getLogger(ScheduleTest.class);

  final List<Step> expected = new ArrayList<Step>(3);

  private Schedule schedule;

  private final DomainFactory factory = new DomainFactory();

  @Test
  void hashCodeEqualsContract() {
    EqualsVerifier.forClass(Schedule.class).withIgnoredFields("iterator").verify();
  }

  @BeforeEach
  void setUp() {
    expected.add(ProcessMother.doughIn());
    expected.add(ProcessMother.betaAmylase());
    expected.add(ProcessMother.alphaAmylase());

    schedule = factory.makeSchedule("Bayrisch Hell", expected);
  }

  @Test
  void canResetScheduleForAnotherRun() {

    runSchedule();

    schedule.reset();

    runSchedule();
  }

  @Test
  void isIdentifiedByName() {
    assertThat(schedule.id()).isEqualTo("Bayrisch Hell");
  }

  @Test
  void executesStepsInSameOrder() {

    final List<Step> executed = runSchedule();

    assertThat(executed).isEqualTo(expected);
  }

  private List<Step> runSchedule() {
    final List<Step> executedSteps = new LinkedList<>();

    assertThat(schedule.hasCompleted()).isFalse();

    while (!schedule.hasCompleted()) {
      final Step currentStep = schedule.currentStep();
      executedSteps.add(currentStep);

      LOG.info("{}", currentStep);
    }

    assertThat(schedule.hasCompleted()).isTrue();

    return executedSteps;
  }
}
