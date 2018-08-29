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

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class EventStore
{
    private final JdbcTemplate           jdbc;
    private final EventSerializerService serializer;

    private EventStore(final JdbcTemplate jdbc, final EventSerializerService serializer)
    {
        this.jdbc = jdbc;
        this.serializer = serializer;
    }

    public static final EventStore with(final JdbcTemplate jdbc)
    {
        return new EventStore(jdbc, EventSerializerService.flatFormat());
    }

    public void record(final MutatorEvent event) throws IOException
    {
        jdbc.update("INSERT INTO events (id, timestamp, message) VALUES (?, ?,?)",
                    event.getClass().getName(),
                    Instant.ofEpochMilli(event.getTimestamp()),
                    serializer.transform(event));
    }

    public <T extends MutatorEvent> List<T> find(Class<T> id)
    {

        return jdbc.query("SELECT id, timestamp, message FROM events where id=?",
                          new Object[] { id.getName() },
                          new RowMapper<T>() {

                              @Override
                              public T mapRow(ResultSet rs, int rowNum) throws SQLException
                              {
                                  try {
                                      return serializer.restore(rs.getString("message"), id);
                                  } catch (IOException e) {
                                      throw new SQLException(e);
                                  }
                              }
                          });
    }
}
