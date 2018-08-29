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
package me.nguba.gambrinus.query.temperature.read;

import me.nguba.gambrinus.cqrs.query.Result;
import me.nguba.gambrinus.process.Temperature;

import java.util.Optional;

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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReadTemperatureResult other = (ReadTemperatureResult) obj;
        if (temperature == null) {
            if (other.temperature != null) {
                return false;
            }
        } else if (!temperature.equals(other.temperature)) {
            return false;
        }
        return true;
    }

    public static ReadTemperatureResult of(final Temperature temperature)
    {
        return new ReadTemperatureResult(temperature);
    }

}
