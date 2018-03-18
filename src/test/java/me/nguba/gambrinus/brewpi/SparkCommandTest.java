package me.nguba.gambrinus.brewpi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SparkCommandTest {

  private static void assertToStringReturns(String expected, SparkCommand command) {
    assertThat(command.toString()).isEqualTo(expected);
  }

  @Test
  void deviceApply() {
    assertToStringReturns("l", SparkCommand.DEVICE_APPLY);
  }

  @Test
  void devices() {
    assertToStringReturns("u", SparkCommand.DEVICES);
  }

  @Test
  void notInstalledDevices() {
    assertToStringReturns("d", SparkCommand.DEVICES_NOT_INSTALLED);
  }

  @Test
  void lcd() {
    assertToStringReturns("h", SparkCommand.LCD);
  }

  @Test
  void reset() {
    assertToStringReturns("E", SparkCommand.RESET);
  }

  @Test
  void set() {
    assertToStringReturns("s", SparkCommand.SET);
  }

  @Test
  void settings() {
    assertToStringReturns("t", SparkCommand.SETTINGS);
  }

  @Test
  void temperature() {
    assertToStringReturns("j", SparkCommand.TEMPERATURE);
  }
}
