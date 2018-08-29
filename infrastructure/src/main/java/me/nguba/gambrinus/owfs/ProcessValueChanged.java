/*
    Copyright (C) 2018  Nicolai P. Guba

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.event.MutatorEvent;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.process.Temperature;

import java.time.Instant;

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
        if (address == null) {
            throw new IllegalArgumentException("OneWireAddres cannot be null.");
        }

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
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
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
