package me.nguba.brauhaus.domain.hardware;

/**
 * Real OneWire addresses for use in unit testing.
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public enum SensorAddress {
  BOIL_KETTLE("2851B75D07000026"),
  HOT_LIQUOR_TANK("2891B95D0700001B"),
  MASH_TUN("282D603507000040");

  private String value;

  SensorAddress(final String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
