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
package me.nguba.gambrinus.ddd;

/**
 * Value Objects are object that <em>describe things</em>.
 * <p>
 * In contrast to {@link Entity} objects, Value Objects have no identity and are immutable. They
 * represent elements of the design that we care about only for <em>what</em> they are, not
 * <em>who</em> or <em>which</em> they are.
 * </p>
 * <p>
 * Value Objects are often passed as parameters in messages (methods) between objects. They are
 * frequently transient and are often used as attributes of {@link Entity} objects and sometimes
 * even other Value Objects. Sometimes they even reference other {@link Entity} objects.
 * </p>
 * <p>
 * When required, Value Objects are often part of a shared kernel to encourage software re-use and
 * consistency across projects. This sharing should happen judiciously.
 * </p>
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 * @see Entity
 * @see Service
 * @see Aggregate
 */
public interface ValueObject
{
}
