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
package me.nguba.gambrinus.query.onewire;

import me.nguba.gambrinus.cqrs.query.QueryHandler;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.owfs.OwfsRoot;
import me.nguba.gambrinus.owfs.OwfsSensor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class FindOneWireAddressesHandler
        implements QueryHandler<FindOneWireAddresses, FindOneWireAddressResult>
{
    public static FindOneWireAddressesHandler on()
    {
        return new FindOneWireAddressesHandler();
    }

    private FindOneWireAddressesHandler()
    {
        super();
    }

    @Override
    public FindOneWireAddressResult query(final FindOneWireAddresses query)
    {
        final OwfsRoot root = OwfsRoot.of(query.getMountpoint());
        final Set<OneWireAddress> addresses = new HashSet<>();

        for (final OwfsSensor sensor : root.listSensors())
            addresses.add(sensor.getId());
        return FindOneWireAddressResult
                .from(addresses.toArray(new OneWireAddress[addresses.size()]));
    }

    @Override
    public void validate(final FindOneWireAddresses query, final Errors errors)
    {
        final OwfsRoot root = OwfsRoot.of(query.getMountpoint());
        if (!root.isValid())
            errors.add(Reason.from(String.format("Invalid mountpoint: %s", root)));
    }

}
