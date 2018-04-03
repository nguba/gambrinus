package me.nguba.gambrinus.process;

import me.nguba.gambrinus.process.Degree;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DegreeTest {

  @Test
  @DisplayName("Symbol for Celsius")
  void symbolFor_Celsius() {
    assertThat(Degree.CELSIUS.toString()).isEqualTo("C");
  }

  @Test
  @DisplayName("Symbol for Kelvin")
  void symbolFor_Kelvin() {
    assertThat(Degree.KELVIN.toString()).isEqualTo("K");
  }

  @Test
  @DisplayName("Symbol for Farenheit")
  void symbolFor_Farenheit() {
    assertThat(Degree.FARENHEIT.toString()).isEqualTo("F");
  }

}
