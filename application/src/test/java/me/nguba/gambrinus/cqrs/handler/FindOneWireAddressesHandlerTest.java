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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.command.FindOneWireAddresses;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.onewire.OneWireAddress;

class FindOneWireAddressesHandlerTest
{
    private final Errors errors = Errors.empty();

    private final FindOneWireAddressesHandler handler = FindOneWireAddressesHandler.create();

    @Test
    void emptyResultOnIOFailure()
    {
        final Set<OneWireAddress> result = handler
                .query(FindOneWireAddresses.on("unavailable"));

        assertThat(result).isEmpty();
    }

    @Test
    void result()
    {
        final Set<OneWireAddress> result = handler
                .query(FindOneWireAddresses.on("src/test/resources/owfs"));

        assertThat(result).containsOnly(OneWireAddress.of("28.273B5D070000"),
                                        OneWireAddress.of("28.4BBB68080000"));
    }

    @Test
    void validation()
    {
        handler.validate(FindOneWireAddresses.on("src/test/resources/owfs"), errors);

        assertThat(errors.hasErrors()).isFalse();
    }

    @Test
    void validationFailure()
    {
        handler.validate(FindOneWireAddresses.on("unavailable"), errors);

        assertThrows(ValidationFailed.class, () -> errors.verify());
    }

}
