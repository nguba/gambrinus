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
package me.nguba.gambrinus.cqrs.handler;

import me.nguba.gambrinus.command.FindOneWireAddresses;
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
        implements QueryHandler<FindOneWireAddresses, Set<OneWireAddress>>
{
    public static FindOneWireAddressesHandler create()
    {
        return new FindOneWireAddressesHandler();
    }

    private FindOneWireAddressesHandler()
    {
        super();
    }

    @Override
    public Set<OneWireAddress> query(final FindOneWireAddresses query)
    {
        final OwfsRoot            root      = OwfsRoot.of(query.getMountpoint());
        final Set<OneWireAddress> addresses = new HashSet<>();

        // TODO use a repository for this
        for (final OwfsSensor sensor : root.listSensors())
            addresses.add(sensor.getId());
        return addresses;
    }

    @Override
    public void validate(final FindOneWireAddresses query, final Errors errors)
    {
        final OwfsRoot root = OwfsRoot.of(query.getMountpoint());
        if (!root.isValid())
            errors.add(Reason.from(String.format("Invalid mountpoint: %s", root)));
    }

}
