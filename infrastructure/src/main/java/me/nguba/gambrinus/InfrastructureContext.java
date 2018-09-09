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
package me.nguba.gambrinus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import me.nguba.gambrinus.event.EventPublisher;
import me.nguba.gambrinus.event.EventSourceMediator;
import me.nguba.gambrinus.event.EventStore;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
@Configuration
public class InfrastructureContext
{
    @Bean
    public EventPublisher eventPublisher()
    {
        return GuavaEventPublisher.create();
    }

    @Bean
    public EventSourceMediator eventSourceListener(final EventPublisher publisher,
                                                   final EventStore store)
    {
        return EventSourceMediator.connect(publisher, store);
    }

    @Bean
    public EventStore eventStore(final JdbcTemplate jdbc)
    {
        return EventStore.with(jdbc);
    }
}
