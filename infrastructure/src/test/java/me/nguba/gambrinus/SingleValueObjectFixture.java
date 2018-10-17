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
package me.nguba.gambrinus;

import me.nguba.gambrinus.ddd.support.SingleValueObject;
import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public abstract class SingleValueObjectFixture<I, V extends SingleValueObject<I>>
{
    private final V valueObject = makeValueObject();

    @Test
    @DisplayName("equality contract is upheld")
    void equalityContract()
    {
        EqualsVerifier.forClass(valueObject.getClass()).usingGetClass().verify();
    }

    protected V getValueObject()
    {
        return valueObject;
    }

    @Test
    @DisplayName("Is valid when not null")
    protected void isValidWhenNotNull()
    {
        assertThat(valueObject.isValid()).isTrue();
    }

    protected abstract V makeValueObject();

    @Test
    @DisplayName("toString() returns value as string")
    void toStringReturnsValueAsString()
    {
        assertThat(valueObject.toString()).isEqualTo(valueObject.getValue().toString());
    }

}
