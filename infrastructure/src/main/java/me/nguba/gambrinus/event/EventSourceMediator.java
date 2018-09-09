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

import java.io.IOException;

import com.google.common.eventbus.Subscribe;

import me.nguba.gambrinus.event.ProcessValueChanged;
import me.nguba.gambrinus.event.sources.ProcessValueChangedSource;

/**
 * Listens to event sourced events and persists them in the event store.
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class EventSourceMediator
{
    public static EventSourceMediator connect(final EventPublisher publisher,
                                              final EventStore store)
    {
        final EventSourceMediator eventSourceListener = new EventSourceMediator(store);
        publisher.subscribe(eventSourceListener);
        return eventSourceListener;
    }

    private final EventStore store;

    private EventSourceMediator(final EventStore store)
    {
        this.store = store;
    }

    @Subscribe
    public void record(final ProcessValueChanged event) throws IOException
    {
        store.record(ProcessValueChangedSource.from(event));
    }
}
