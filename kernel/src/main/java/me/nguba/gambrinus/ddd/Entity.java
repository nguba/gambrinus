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
 * An object defined primarily by its identiy and <em>not</em> by their attributes.
 * <p>
 * Entities have special modelling and design considerations. They have lifecycles that can
 * radically change their form or content, but a thread of continuity must be maintained. They
 * represent a thread of identity that runs through time and often across distinct representations.
 * </p>
 * <p>
 * Sometimes such an object must be matched with another object even though their attributes differ.
 * An object must be distinguished from other objects even though they may have the same attributes.
 * </p>
 * <p>
 * Their identities must be defined so that they can be effectively tracked. Their class
 * definitions, responsiblities, atrributes and associations should revolve around who they are,
 * rather than the particular attributes they carry.
 * </p>
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 * @param <I>
 * @see Service
 * @see ValueObject
 * @see Aggregate
 */
public abstract class Entity<I>
{
    private final I id;

    protected Entity(final I id)
    {
        this.id = id;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Entity<?> other = (Entity<?>) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    /**
     * Unique identifier to distinguish objects even though they may have the same attributes.
     *
     * @return the identity
     */
    public I getId()
    {
        return id;
    }

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
        return result;
    }
}
