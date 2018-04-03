package me.nguba.gambrinus.io;

import me.nguba.gambrinus.domain.process.Schedule;
import me.nguba.gambrinus.domain.process.Step;
import me.nguba.gambrinus.domain.process.Temperature;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.InputStream;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

class BeerXmlParserTest {
  private final BeerXmlParser parser = new BeerXmlParser();
  private InputStream         stream;

  @Test
  void parseRecipeName() throws Exception {
    final Schedule recipe = parser.parse(stream);
    assertThat(recipe.toString()).isEqualTo("Bayrisch Hell");
  }

  @Test
  public void parseSchedule() throws Exception {
    final Schedule expected = parser.parse(stream);

    final List<Step> steps = new LinkedList<>();
    steps.add(Step.valueOf("Einmaischen", Temperature.valueOf(66.0), Duration.ZERO));
    steps.add(Step
        .valueOf("Maltoserast (60°-65°)", Temperature.valueOf(63.0), Duration.ofMinutes(35)));
    steps.add(Step
        .valueOf("Maltoserast (60°-65°)", Temperature.valueOf(65.0), Duration.ofMinutes(30)));
    steps.add(Step
        .valueOf("Kombirast (66°-69°)", Temperature.valueOf(68.0), Duration.ofMinutes(10)));
    steps.add(Step
        .valueOf("Verzuckerung (70°-75°)", Temperature.valueOf(70.0), Duration.ofMinutes(40)));
    steps.add(Step.valueOf("Abmaischen (78°)", Temperature.valueOf(77.0), Duration.ofMinutes(10)));

    final Schedule actual = new Schedule("Bayrisch Hell", steps);

    assertThat(actual.getSteps()).isEqualTo(expected.getSteps());
  }

  @BeforeEach
  void setUp() throws Exception {
    stream = getClass().getResourceAsStream("/Bayrisch_Hell.xml");
    assertNotNull(stream);

  }
}
