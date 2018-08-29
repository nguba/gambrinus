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

import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.event.MutatorEvent;
import me.nguba.gambrinus.process.Temperature;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 *
 */
public final class EventSerializerService
{
    private final ObjectMapper mapper;

    private EventSerializerService(final ObjectMapper mapper)
    {
        this.mapper = mapper;
    }

    public static EventSerializerService flatFormat()
    {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
        mapper.configure(MapperFeature.AUTO_DETECT_IS_GETTERS, false);
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        SimpleModule module = new SimpleModule();
        module.addDeserializer(VesselId.class, new VesselIdDeserailizer());
        module.addDeserializer(Temperature.class, new TemperatureDeserializer());
        mapper.registerModule(module);
        
        return new EventSerializerService(mapper);
    }

    public <E extends MutatorEvent> String transform(final E event) throws IOException
    {
        return mapper.writeValueAsString(event);
    }

    public <E extends MutatorEvent> E restore(String string, Class<E> type) throws IOException
    {
        return mapper.readValue(string, type);
    }
}
