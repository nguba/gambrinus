package me.nguba.gambrinus.brewpi;

public enum SparkCommand {

  DEVICE_APPLY("l"), // l
  DEVICES("u"), // U
  DEVICES_NOT_INSTALLED("d"), // d
  LCD("h"), // h
  RESET("E"), // E
  SET("s"), // s
  SETTINGS("t"), // t
  TEMPERATURE("j"); // j

  private final String value;

  SparkCommand(final String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
