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

package me.nguba.gambrinus.eventstore;

import me.nguba.gambrinus.event.MutatorEvent;

import java.time.Instant;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ValueObjectEvent extends MutatorEvent
{
    private Object valueObject;

    protected ValueObjectEvent()
    {
    }

    private ValueObjectEvent(Object valueObject) {
        super(Instant.now());
        this.valueObject = valueObject;     
    }
    
    public static final ValueObjectEvent from(Object valueObject) {
        return new ValueObjectEvent(valueObject);
    }
    
    public Object getValueObject()
    {
        return valueObject;
    }

    public void setValueObject(Object valueObject)
    {
        this.valueObject = valueObject;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((valueObject == null) ? 0 : valueObject.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ValueObjectEvent other = (ValueObjectEvent) obj;
        if (valueObject == null) {
            if (other.valueObject != null)
                return false;
        } else if (!valueObject.equals(other.valueObject))
            return false;
        return true;
    }
}
