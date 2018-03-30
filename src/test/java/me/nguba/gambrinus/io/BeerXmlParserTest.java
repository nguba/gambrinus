package me.nguba.gambrinus.io;

import me.nguba.gambrinus.domain.process.Schedule;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.InputStream;

class BeerXmlParserTest {
  private final BeerXmlParser parser = new BeerXmlParser();
  private InputStream         stream;

  @Test
  void parseRecipeName() throws Exception {
    final Schedule recipe = parser.parse(stream);
    assertThat(recipe.toString()).isEqualTo("Bayrisch Hell");
  }
  //
  // @Test
  // public void parseSchedule() throws Exception {
  // final Schedule recipe = parser.parse(stream);
  // final List<Step> expected = DomainTestFactory.INSTANCE.createSchedule();
  // assertThat(recipe.getSchedule()).asList().isEqualTo(expected);
  // }

  @BeforeEach
  void setUp() throws Exception {
    stream = getClass().getResourceAsStream("/Bayrisch_Hell.xml");
    assertNotNull(stream);

  }
}
