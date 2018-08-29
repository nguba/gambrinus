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
package me.nguba.gambrinus.raspberry;

import me.nguba.gambrinus.ddd.support.SingleValueObject;

import com.pi4j.io.gpio.Pin;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class GPIO extends SingleValueObject<Pin>
{
    private GPIO(final Pin value)
    {
        super(value);
    }

    public static GPIO from(final Pin pin)
    {
        return new GPIO(pin);
    }
}
