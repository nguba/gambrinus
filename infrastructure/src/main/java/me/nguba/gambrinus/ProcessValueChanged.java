package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.command.CommandEvent;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.process.Temperature;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ProcessValueChanged implements CommandEvent
{
    public static ProcessValueChanged on(final OneWireAddress address, final Temperature expected)
    {
        if (address == null) {
            throw new IllegalArgumentException("OneWireAddres cannot be null.");
        }
        return new ProcessValueChanged(address, expected);
    }

    private final Temperature processValue;

    private final OneWireAddress address;

    private ProcessValueChanged(final OneWireAddress adddress, final Temperature processValue)
    {
        this.address = adddress;
        this.processValue = processValue == null ? Temperature.celsius(0) : processValue;
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
        final StringBuilder builder = new StringBuilder();
        builder.append("ProcessValueChanged [address=").append(address).append(", processValue=")
                .append(processValue).append("]");
        return builder.toString();
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((processValue == null) ? 0 : processValue.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProcessValueChanged other = (ProcessValueChanged) obj;
        if (address == null) {
            if (other.address != null) {
                return false;
            }
        } else if (!address.equals(other.address)) {
            return false;
        }
        if (processValue == null) {
            if (other.processValue != null) {
                return false;
            }
        } else if (!processValue.equals(other.processValue)) {
            return false;
        }
        return true;
    }
}
