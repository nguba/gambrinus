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

import me.nguba.gambrinus.event.sources.EventSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class EventStore
{
    private static final Logger LOGGER = LoggerFactory.getLogger(EventStore.class);

    public static EventStore with(final JdbcTemplate jdbc)
    {
        return new EventStore(jdbc, EventSerializer.flatFormat());
    }

    private final JdbcTemplate jdbc;

    private final EventSerializer serializer;

    private EventStore(final JdbcTemplate jdbc, final EventSerializer serializer)
    {
        this.jdbc = jdbc;
        this.serializer = serializer;
    }

    public <T extends EventSource> List<T> find(final Class<T> id)
    {
        return jdbc.query("SELECT id, timestamp, source FROM events where id=?",
                          new Object[] { id.getName() },
                          (RowMapper<T>) (rs, rowNum) -> {
                              try {
                                  return serializer.restore(rs.getString("source"), id);
                              } catch (final IOException e) {
                                  throw new SQLException(e);
                              }
                          });
    }

    public void record(final EventSource source) throws IOException
    {
        LOGGER.info("Storing: {}", source);
        jdbc.update("INSERT INTO events (id, timestamp, source) VALUES (?, ?,?)",
                    source.getClass().getName(),
                    Instant.ofEpochMilli(source.getTimestamp()),
                    serializer.transform(source));
    }
}
