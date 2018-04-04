package me.nguba.gambrinus.brewpi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

  private final Map<String, Object> properties = new HashMap<String, Object>();

  /**
   * Properties to send along with this command.
   *
   * @return unmodifiable map of properties
   */
  public Map<String, Object> getProperties() {
    return Collections.unmodifiableMap(properties);
  }

  public Object put(final String key, final Object value) {
    return properties.put(key, value);
  }
}
