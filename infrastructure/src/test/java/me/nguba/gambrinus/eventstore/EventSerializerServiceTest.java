/*
 * Copyright (C) 2018 Nicolai P. Guba This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version. This program
 * is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details. You should have received a copy of the GNU General Public
 * License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package me.nguba.gambrinus.eventstore;

import me.nguba.gambrinus.event.MutatorEvent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

public class EventSerializerServiceTest extends MutatorEvent
{

    public String one = "One value";

    public Integer two = Integer.valueOf(2);

    public EventSerializerServiceTest()
    {
        super(Instant.now());
    }

    @Test
    void transforToUnindentedString() throws Exception
    {
        final String transform = EventSerializerService.flatFormat().transform(this);

        assertEquals(String
                .format("{\"timestamp\":%d,\"one\":\"One value\",\"two\":2}",
                        timestamp),
                     transform);
    }

}
