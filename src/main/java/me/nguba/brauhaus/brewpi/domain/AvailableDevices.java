package me.nguba.brauhaus.brewpi.domain;

import me.nguba.brauhaus.domain.Aggregate;

import java.util.LinkedList;
import java.util.List;

/**
 * Message from BrewPi Spark.
 *
 * <p>
 * To use the board for our purposes we use this collective message for device status and its
 * readings. It is not as fast as I would like but it's the only way I can use the spark board
 * without having the firmware interfere with its own logic.
 * </p>
 * <p>
 * It replies with a list of available devices for each one wire address where a sensor is
 * available.
 * </p>
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 * @see Device
 */
public final class AvailableDevices implements Aggregate {

  private final List<Device> h = new LinkedList<>();

  public boolean add(final Device e) {
    return h.add(e);
  }

  public List<Device> getH() {
    return h;
  }

  private AvailableDevices() {
    super();
  }

  public static AvailableDevices availableDevices() {
    return new AvailableDevices();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((h == null) ? 0 : h.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final AvailableDevices other = (AvailableDevices) obj;
    if (h == null) {
      if (other.h != null) {
        return false;
      }
    } else if (!h.equals(other.h)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("AvailableDevices [h=").append(h).append("]");
    return builder.toString();
  }
}
