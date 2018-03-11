package me.nguba.gambrinus.brewpi.domain;

import me.nguba.gambrinus.domain.Entity;
import me.nguba.gambrinus.domain.hardware.OneWireAddress;

/**
 * Message from BrewPi Spark.
 *
 * <p>
 * Only the address and the value are used. We are fine with hard-coded values for the unused items.
 * </p>
 * 
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Device implements Entity<OneWireAddress> {

  private final int i = -1;

  private final int t = 0;

  private final int c = 1;

  private final int b = 0;

  private final int f = 0;

  private final int h = 2;

  private final int d = 0;

  private final int p = 0;

  private final OneWireAddress a;

  private final double v;

  private Device(final OneWireAddress a, final double v) {
    this.a = a;
    this.v = v;
  }

  @Override
  public OneWireAddress id() {
    return a;
  }

  public static Device device(final OneWireAddress address, final double value) {
    return new Device(address, value);
  }

  public int getI() {
    return i;
  }

  public int getT() {
    return t;
  }

  public int getC() {
    return c;
  }

  public int getB() {
    return b;
  }

  public int getF() {
    return f;
  }

  public int getH() {
    return h;
  }

  public int getD() {
    return d;
  }

  public int getP() {
    return p;
  }

  public OneWireAddress getA() {
    return a;
  }

  public double getV() {
    return v;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((a == null) ? 0 : a.hashCode());
    long temp;
    temp = Double.doubleToLongBits(v);
    result = prime * result + (int) (temp ^ (temp >>> 32));
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
    final Device other = (Device) obj;
    if (a == null) {
      if (other.a != null) {
        return false;
      }
    } else if (!a.equals(other.a)) {
      return false;
    }
    if (Double.doubleToLongBits(v) != Double.doubleToLongBits(other.v)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("Device [a=").append(a).append(", v=").append(v).append("]");
    return builder.toString();
  }

}
