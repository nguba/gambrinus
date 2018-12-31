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
package me.nguba.gambrinus.event;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 *
 */
public final class EventSerializer
{
    private static final Logger LOGGER = LoggerFactory.getLogger(EventSerializer.class);

    public static EventSerializer flatFormat()
    {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
        mapper.configure(MapperFeature.AUTO_DETECT_IS_GETTERS, false);
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return new EventSerializer(mapper);
    }

    private final ObjectMapper mapper;

    private EventSerializer(final ObjectMapper mapper)
    {
        this.mapper = mapper;
    }

    public <E> E restore(final String string, final Class<E> type) throws IOException
    {
        return mapper.readValue(string, type);
    }

    public String transform(final Object event) throws IOException
    {
        final String value = mapper.writeValueAsString(event);
        LOGGER.trace("{}", value);
        return value;
    }
}
