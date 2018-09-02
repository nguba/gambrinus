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
package me.nguba.gambrinus.eventstore.sources;

import me.nguba.gambrinus.command.temperature.process.ProcessValueChanged;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.eventstore.EventSerializerService;
import me.nguba.gambrinus.process.Temperature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class ProcessValueChangedSourceTest
{
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ProcessValueChangedSourceTest.class);

    private final ProcessValueChanged event = ProcessValueChanged.on(VesselId.of("Boil Kettle"),
                                                                     temperature.toCelsius());

    private final EventSerializerService serializer = EventSerializerService.flatFormat();

    private final Temperature temperature = Temperature.celsius(10);

    @Test
    void canDeserialize() throws Exception
    {
        final String json = serialize(ProcessValueChangedSource.from(event));

        final ProcessValueChangedSource restore = serializer
                .restore(json, ProcessValueChangedSource.class);

        LOGGER.info("{}", restore);
        assertThat(restore).isEqualToComparingFieldByField(ProcessValueChangedSource.from(event));
    }

    @Test
    void canSerialize() throws Exception
    {
        final String json = serialize(ProcessValueChangedSource.from(event));
        assertThat(json).contains("timestamp", ":10.0", "C");
    }

    private String serialize(final Object event) throws IOException
    {
        final String json = serializer.transform(event);
        LOGGER.info("{}", json);
        return json;
    }
}
