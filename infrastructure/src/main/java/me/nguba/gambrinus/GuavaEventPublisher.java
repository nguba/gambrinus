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

import me.nguba.gambrinus.event.EventPublisher;
import me.nguba.gambrinus.event.MutatorEvent;

import com.google.common.eventbus.EventBus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default event publisher that logs to the configured logging system but does not broadcast any
 * events to subscribers. Useful during testing.
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class GuavaEventPublisher implements EventPublisher
{
    private static final Logger LOGGER = LoggerFactory.getLogger(EventPublisher.class);

    public static GuavaEventPublisher create()
    {
        return new GuavaEventPublisher();
    }

    private final EventBus bus = new EventBus();

    private GuavaEventPublisher()
    {
        super();
    }

    @Override
    public <E extends MutatorEvent> void publish(final E event)
    {
        LOGGER.trace("{}", event);
        bus.post(event);
    }

    @Override
    public void subscribe(final Object recipient)
    {
        bus.register(recipient);
    }

}
