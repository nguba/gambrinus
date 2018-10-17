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
package me.nguba.gambrinus.onewire;

import me.nguba.gambrinus.SingleValueObjectFixture;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class OneWireAddressTest extends SingleValueObjectFixture<String, OneWireAddress>
{
    @Test
    void isEmpty()
    {
        assertThat(OneWireAddress.of("").isValid()).isFalse();
    }

    @Test
    void isNull()
    {
        assertThat(OneWireAddress.of(null).isValid()).isFalse();
    }

    @Test
    void isShort()
    {
        final String shortAddress = getValueObject().getValue().substring(1);
        assertThat(OneWireAddress.of(shortAddress).isValid()).isFalse();
    }

    @Override
    protected OneWireAddress makeValueObject()
    {
        return OneWireAddress.of("28.4BBB68080000");
    }
}
