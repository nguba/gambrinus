package me.nguba.gambrinus.process;

import me.nguba.gambrinus.ddd.ValueObject;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Temperature implements ValueObject
{
  public enum Scale implements ValueObject
  {
    CELSIUS("C"), KELVIN("K"), FARENHEIT("F");

    private final String symbol;

    Scale(final String symbol)
    {
      this.symbol = symbol;
    }

    @Override
    public String toString()
    {
      return symbol;
    }
  }

  private final double value;

  private final Scale scale;

  private Temperature(final double value, final Scale scale)
  {
    this.value = value;
    this.scale = scale;
  }

  public static Temperature celsius(final double value)
  {
    return new Temperature(value, Scale.CELSIUS);
  }

  public static Temperature farenheit(final double value)
  {
    return new Temperature(value, Scale.FARENHEIT);
  }

  public static Temperature kelvin(final double value)
  {
    return new Temperature(value, Scale.KELVIN);
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + (scale == null ? 0 : scale.hashCode());
    long temp;
    temp = Double.doubleToLongBits(value);
    result = prime * result + (int) (temp ^ temp >>> 32);
    return result;
  }

  @Override
  public boolean equals(final Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final Temperature other = (Temperature) obj;
    if (scale != other.scale)
      return false;
    if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
      return false;
    return true;
  }

  @Override
  public String toString()
  {
    final StringBuilder builder = new StringBuilder();
    builder.append(value).append(" (").append(scale).append(")");
    return builder.toString();
  }

  public Temperature toKelvin()
  {
    switch (scale) {
      case CELSIUS: {
        final double c = value + 273.15;
        return Temperature.kelvin(truncate(c));
      }
      case FARENHEIT: {
        final double k = (value + 459.67) * (5.0 / 9.0);
        return Temperature.kelvin(truncate(k));
      }
      default:
        return this;
    }
  }

  private static double truncate(final double c)
  {
    return Math.floor(c * 100) / 100;
  }

  public Temperature toCelsius()
  {
    switch (scale) {
      case FARENHEIT: {
        final double c = (value - 32) * 0.5556;
        return Temperature.celsius(truncate(c));
      }
      case KELVIN: {
        final double c = value - 273.15;
        return Temperature.celsius(truncate(c));
      }
      default:
        return this;
    }
  }

  public Temperature toFarenheit()
  {
    switch (scale) {
      case CELSIUS: {
        final double f = 9.0 / 5.0 * value + 32;
        return Temperature.farenheit(truncate(f));
      }
      case KELVIN: {
        final double f = (value - 273.15) * 9.0 / 5.0 + 32;
        return Temperature.farenheit(truncate(f));
      }
      default:
        return this;
    }
  }

  public double getValue()
  {
    return value;
  }

  public Scale getScale()
  {
    return scale;
  }
}
