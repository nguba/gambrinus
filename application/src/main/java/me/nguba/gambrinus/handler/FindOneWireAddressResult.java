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
package me.nguba.gambrinus.handler;

import me.nguba.gambrinus.cqrs.query.Result;
import me.nguba.gambrinus.onewire.OneWireAddress;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class FindOneWireAddressResult implements Result<Set<OneWireAddress>>
{
    public static FindOneWireAddressResult from(final OneWireAddress[] addresses)
    {
        if (addresses == null)
            return new FindOneWireAddressResult(new HashSet<>());

        final Set<OneWireAddress> result = new HashSet<>();
        for (final OneWireAddress address : addresses)
            result.add(address);
        return new FindOneWireAddressResult(result);
    }

    private final Set<OneWireAddress> result;

    private FindOneWireAddressResult(final Set<OneWireAddress> result)
    {
        this.result = result;
    }

    @Override
    public Optional<Set<OneWireAddress>> getResult()
    {
        return Optional.of(result);
    }
}
