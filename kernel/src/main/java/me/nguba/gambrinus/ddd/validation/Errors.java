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
package me.nguba.gambrinus.ddd.validation;

import me.nguba.gambrinus.ddd.ValueObject;

import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Errors implements ValueObject
{
    public static Errors empty()
    {
        return new Errors();
    }

    private final Set<Reason> errors = new HashSet<>();

    public void add(final Reason reason)
    {
        errors.add(reason);
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
        final Errors other = (Errors) obj;
        if (errors == null) {
            if (other.errors != null)
                return false;
        } else if (!errors.equals(other.errors))
            return false;
        return true;
    }

    public boolean has(final Reason reason)
    {
        return errors.contains(reason);
    }

    public boolean hasErrors()
    {
        return !errors.isEmpty();
    }

    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = prime * result + (errors == null ? 0 : errors.hashCode());
        return result;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        for (final Reason reason : errors)
            builder.append(reason).append("\n");
        return builder.toString();
    }

    public void verify() throws ValidationFailed
    {
        if (!errors.isEmpty())
            throw new ValidationFailed(this);
    }
}
