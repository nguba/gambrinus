package me.nguba.gambrinus.query.temperature.read;

import java.util.Optional;

import me.nguba.gambrinus.cqrs.query.Result;
import me.nguba.gambrinus.process.Temperature;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ReadTemperatureResult implements Result<Temperature>
{
  @Override
  public Optional<Temperature> getResult()
  {
    return temperature;
  }

  private ReadTemperatureResult(final Temperature temperature)
  {
    this.temperature = Optional.of(temperature);
  }

  private final Optional<Temperature> temperature;

  @Override
  public String toString()
  {
    final StringBuilder builder = new StringBuilder();
    builder.append("TemperatureResult [temperature=").append(temperature).append("]");
    return builder.toString();
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + (temperature == null ? 0 : temperature.hashCode());
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
    final ReadTemperatureResult other = (ReadTemperatureResult) obj;
    if (temperature == null) {
      if (other.temperature != null)
        return false;
    } else if (!temperature.equals(other.temperature))
      return false;
    return true;
  }

  public static ReadTemperatureResult of(final Temperature temperature)
  {
    return new ReadTemperatureResult(temperature);
  }

}
