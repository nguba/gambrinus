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

import me.nguba.gambrinus.event.EventPublisher;

import com.google.common.eventbus.Subscribe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Listens to event sourced events and persists them in the event store.
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class EventSourceListener
{
    private static final Logger LOGGER = LoggerFactory.getLogger(EventSourceListener.class);

    private final EventStore store;

    private EventSourceListener(final EventStore store)
    {
        this.store = store;
    }

    public static final EventSourceListener connect(final EventPublisher publisher,
                                                 final EventStore store)
    {
        final EventSourceListener eventSourceListener = new EventSourceListener(store);
        publisher.subscribe(eventSourceListener);
        return eventSourceListener;
    }

    @Subscribe
    public void callback(final EventSource source)
    {
        LOGGER.info("<<< {}", source);
    }
}
