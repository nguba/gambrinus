package me.nguba.gambrinus.process;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TemperatureTest {

  private static void assertCelsius(final double source, final Degree from, final double result) {
    assertConversion(source, from, result, Degree.CELSIUS);
  }

  private static void assertConversion(final double source,
                                       final Degree from,
                                       final double result,
                                       final Degree to) {
    assertThat(Temperature.valueOf(source, from).convert(to))
        .isEqualTo(Temperature.valueOf(result, to));
  }

  private static void assertFarenheit(final double source, final Degree from, final double result) {
    assertConversion(source, from, result, Degree.FARENHEIT);
  }

  private static void assertKelvin(final double source, final Degree from, final double result) {
    assertConversion(source, from, result, Degree.KELVIN);
  }

  @Test
  @DisplayName("Celsius to Celsius")
  void convertCelsisuToCelsius() {
    assertCelsius(103.23, Degree.CELSIUS, 103.23);
  }

  @Test
  @DisplayName("Celsius to Farenheit")
  void convertCelsisuToFarenheit() {
    assertFarenheit(65.32, Degree.CELSIUS, 149.57);
  }

  @Test
  @DisplayName("Celsius to Kelvin")
  void convertCelsiusToKelvin() {
    assertKelvin(103.23, Degree.CELSIUS, 376.38);
  }

  @Test
  @DisplayName("Farenheit to Celsius")
  void convertFarenheitToCelsius() {
    assertCelsius(103.23, Degree.FARENHEIT, 39.57);
  }

  @Test
  @DisplayName("Farenheit to Kelvin")
  void convertFarenheitToKelvin() {
    assertKelvin(103.23, Degree.FARENHEIT, 312.72);
  }

  @Test
  @DisplayName("Farenheit to Farenheit")
  void convertFarenheitToFarenheit() {
    assertFarenheit(103.23, Degree.FARENHEIT, 103.23);
  }

  @Test
  @DisplayName("Kelvin to Celsius")
  void convertKelvinToCelsius() {
    assertCelsius(103.23, Degree.KELVIN, -169.92);
  }

  @Test
  @DisplayName("Kelvin to Farenheit")
  void convertKelvinToFarenheit() {
    assertFarenheit(103.23, Degree.KELVIN, -273.86);
  }

  @Test
  @DisplayName("Kelvin to Kelvin")
  void convertKelvinToKelvin() {
    assertKelvin(103.23, Degree.KELVIN, 103.23);
  }

  @Test
  @DisplayName("0 Celsius to Celsius")
  void convertZeroCelsisuToCelsius() {
    assertCelsius(0, Degree.CELSIUS, 0);
  }

  @Test
  @DisplayName("0 Celsius to Farenheit")
  void convertZeroCelsisuToFarenheit() {
    assertFarenheit(0, Degree.CELSIUS, 32);
  }

  @Test
  @DisplayName("0 Farenheit to Celsius")
  void convertZeroFarenheitToCelsius() {
    assertCelsius(0, Degree.FARENHEIT, -17.78);
  }

  @Test
  @DisplayName("0 Farenheit to Kelvin")
  void convertZeroFarenheitToKelvin() {
    assertKelvin(0, Degree.FARENHEIT, 255.37);
  }

  @Test
  @DisplayName("0 Kelvin to Celsius")
  void convertZeroKelvinToCelsius() {
    assertCelsius(0, Degree.KELVIN, -273.15);
  }

  @Test
  @DisplayName("0 Kelvin to Farenheit")
  void convertZeroKelvinToFarenheit() {
    assertFarenheit(0, Degree.KELVIN, -459.67);
  }

  @Test
  @DisplayName("0 Kelvin to Kelvin")
  void convertZeroKelvinToKelvin() {
    assertKelvin(0, Degree.KELVIN, 0);
  }

  @Test
  void equalsContract() {
    EqualsVerifier.forClass(Temperature.class).verify();
  }

  @Test
  @DisplayName("Celsius string format")
  void toString_Celsius() {
    assertThat(Temperature.valueOf(20.5, Degree.CELSIUS).toString()).isEqualTo("20.5 (C)");
  }

  @Test
  @DisplayName("Farenheit string format")
  void toString_Farenheit() {
    assertThat(Temperature.valueOf(12.3, Degree.FARENHEIT).toString()).isEqualTo("12.3 (F)");
  }

  @Test
  @DisplayName("Kelvin string format")
  void toString_Kelvin() {
    assertThat(Temperature.valueOf(12.3, Degree.KELVIN).toString()).isEqualTo("12.3 (K)");
  }

  @Test
  @DisplayName("Celsius by default")
  void usesCelsiusByDefault() {
    assertThat(Temperature.valueOf(20.5, Degree.CELSIUS)).isEqualTo(Temperature.valueOf(20.5));
  }
}
