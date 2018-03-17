package me.nguba.gambrinus.domain.process;

import me.nguba.gambrinus.domain.ValueObject;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Temperature implements ValueObject {

  private final double value;

  private final Degree degree;

  private Temperature(final double value, final Degree degree) {
    this.value = value;
    this.degree = degree;
  }

  /**
   * Returns temperature in the {@link Degree#CELSIUS} scale.
   *
   * @param value
   *          - the temperature
   */
  public static Temperature valueOf(final double value) {
    return new Temperature(value, Degree.CELSIUS);
  }

  public static Temperature valueOf(final double value, final Degree degree) {
    return new Temperature(value, degree);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((degree == null) ? 0 : degree.hashCode());
    long temp;
    temp = Double.doubleToLongBits(value);
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
    final Temperature other = (Temperature) obj;
    if (degree != other.degree) {
      return false;
    }
    if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append(value).append(" (").append(degree.symbol).append(")");
    return builder.toString();
  }

  public Temperature convert(final Degree target) {

    switch (target) {

    case FARENHEIT:
      return toFarenheit();
    case KELVIN:
      return toKelvin();
    default:
      return toCelsius();
    }

  }

  private Temperature toKelvin() {
    switch (degree) {
    case CELSIUS: {
      final double c = value + 273.15;
      return Temperature.valueOf(truncate(c), Degree.KELVIN);
    }
    case FARENHEIT: {
      final double k = (value + 459.67) * (5.0 / 9.0);
      return Temperature.valueOf(truncate(k), Degree.KELVIN);
    }
    default:
      return this;
    }
  }

  private static double truncate(final double c) {
    return Math.floor(c * 100) / 100;
  }

  private Temperature toCelsius() {
    switch (degree) {
    case FARENHEIT: {
      final double c = (value - 32) * (0.5556);
      return Temperature.valueOf(truncate(c), Degree.CELSIUS);
    }
    case KELVIN: {
      final double c = value - 273.15;
      return Temperature.valueOf(truncate(c), Degree.CELSIUS);
    }
    default:
      return this;
    }
  }

  private Temperature toFarenheit() {
    switch (degree) {
    case CELSIUS: {
      final double f = (9.0 / 5.0) * value + 32;
      return Temperature.valueOf(truncate(f),
                                 Degree.FARENHEIT);
    }
    case KELVIN: {
      final double f = (((value - 273.15) * 9.0 / 5.0) + 32);
      return Temperature.valueOf(truncate(f),
                                 Degree.FARENHEIT);
    }
    default:
      return this;
    }
  }
}
