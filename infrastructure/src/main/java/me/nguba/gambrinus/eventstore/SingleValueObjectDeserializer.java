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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public abstract class SingleValueObjectDeserializer<T> extends StdDeserializer<T>
{
    private static final long serialVersionUID = 6448077067201529748L;

    public SingleValueObjectDeserializer()
    {
        this(null);
    }

    private SingleValueObjectDeserializer(final Class<?> target)
    {
        super(target);
    }

    @Override
    public final T deserialize(final JsonParser p, final DeserializationContext ctxt)
            throws IOException, JsonProcessingException
    {
        final JsonNode node = p.getCodec().readTree(p);

        System.out.println(node);
        return onValueNode(node.get("value"));
    }

    protected abstract T onValueNode(JsonNode valueNode);

}
