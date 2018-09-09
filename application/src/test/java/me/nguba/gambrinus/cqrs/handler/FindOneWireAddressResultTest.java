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

import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.onewire.OneWireAddress;

class FindOneWireAddressResultTest
{
    @Test
    void noDuplicates()
    {
        final OneWireAddress a = OneWireAddress.of("28.273B5D070000");
        final OneWireAddress b = OneWireAddress.of("28.273B5D070000");
        final OneWireAddress c = OneWireAddress.of("28.4BBB68080000");
        final OneWireAddress[] sensors = { a, b, c };

        assertThat(FindOneWireAddressResult.from(sensors).getResult().get()).containsOnly(a, c);
    }

    @Test
    void nullAddresses()
    {
        assertThat(FindOneWireAddressResult.from(null).getResult().get()).isEmpty();
    }

    @Test
    void returnsEmptySetByDefault()
    {
        final FindOneWireAddressResult sensorsResult = FindOneWireAddressResult
                .from(new OneWireAddress[0]);
        assertThat(sensorsResult.getResult().get()).isEmpty();
    }
}
