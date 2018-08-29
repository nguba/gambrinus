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
 * Services model important domain operations that can't find a natural home in an {@link Entity} or
 * {@link ValueObject}.
 * <p>
 * A Service is an operation offered as an interface that stands alone in the model, without
 * encapsulating state (stateless), as {@link Entity} or {@link ValueObject} do.
 * </p>
 * <p>
 * Services emphasise the relationship with other objects and are defined purely in terms of what
 * they can do for a client. A service tends to be named for an <em>activity</em>, rather than an
 * entity--a verb rather than a noun.
 * </p>
 * <p>
 * Operation names should come from the <em>Ubiquitous Language</em> or be introduced into it.
 * Parameters and results should be domain objects.
 * </p>
 * <p>
 * A good service has the following characteristics
 * <ol>
 * <li>The operation relates to a domain concept that is not a natural part of an {@link Entity} or
 * {@link ValueObject}.
 * <li>The interface is defined in terms of other elements of the domain model.
 * <li>The operation is stateless.
 * </ol>
 * </p>
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 * @see Entity
 * @see ValueObject
 * @see Aggregate
 */
public interface Service
{

}
