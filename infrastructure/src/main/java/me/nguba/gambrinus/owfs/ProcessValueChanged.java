package me.nguba.gambrinus.owfs;

import java.time.Instant;

import me.nguba.gambrinus.event.MutatorEvent;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.process.Temperature;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ProcessValueChanged extends MutatorEvent
{
  public static ProcessValueChanged on(final OneWireAddress address, final Temperature expected)
  {
    return ProcessValueChanged.from(Instant.now(), address, expected);
  }

  public static ProcessValueChanged from(final Instant instant,
                                         final OneWireAddress address,
                                         final Temperature expected)
  {
    if (address == null)
      throw new IllegalArgumentException("OneWireAddres cannot be null.");

    return new ProcessValueChanged(instant,
                                   address,
                                   expected == null ? Temperature.celsius(0) : expected);
  }

  private final Temperature processValue;

  private final OneWireAddress address;

  private ProcessValueChanged(final Instant instant,
                              final OneWireAddress adddress,
                              final Temperature processValue)
  {
    super(instant);
    address = adddress;
    this.processValue = processValue;
  }

  public Temperature getProcessValue()
  {
    return processValue;
  }

  public OneWireAddress getAddress()
  {
    return address;
  }

  @Override
  public String toString()
  {
    return "ProcessValueChanged [processValue=" + processValue + ", address=" + address
        + ", timestamp=" + timestamp
        + "]";
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (address == null ? 0 : address.hashCode());
    result = prime * result + (processValue == null ? 0 : processValue.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj)
  {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    final ProcessValueChanged other = (ProcessValueChanged) obj;
    if (address == null) {
      if (other.address != null)
        return false;
    } else if (!address.equals(other.address))
      return false;
    if (processValue == null) {
      if (other.processValue != null)
        return false;
    } else if (!processValue.equals(other.processValue))
      return false;
    return true;
  }
}
