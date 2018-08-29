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

import java.io.IOException;

import javax.sql.DataSource;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class EventStore
{
    private final DataSource             dataSource;
    private final EventSerializerService serializer;

    private EventStore(final DataSource dataSource, final EventSerializerService serializer)
    {
        this.dataSource = dataSource;
        this.serializer = serializer;
    }

    public static final EventStore with(final DataSource dataSource)
    {
        return new EventStore(dataSource, EventSerializerService.flatFormat());
    }

    public void record(final MutatorEvent event) throws IOException
    {
    }
}
