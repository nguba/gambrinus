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

package me.nguba.gambrinus.process;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A temperature process manages the worker queue for {@link TemperatureUnit} items.
 * <p>
 * It specifies how long the equipment needs to dwell on a given setpoint using a configurable and
 * keeps track of which units have been processed and how much time is reamaing in each.
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class TemperatureProcess implements Iterable<TemperatureUnit>
{
    public static TemperatureProcess empty()
    {
        return new TemperatureProcess();
    }

    private final ConcurrentLinkedQueue<TemperatureUnit> queue;

    private TemperatureProcess()
    {
        queue = new ConcurrentLinkedQueue<>();
    }

    public TemperatureUnit currentUnit()
    {
        return queue.peek();
    }

    @Override
    public Iterator<TemperatureUnit> iterator()
    {
        return queue.iterator();
    }

    public void schedule(final TemperatureUnit temperatureUnit)
    {
        queue.add(temperatureUnit);
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("TemperatureProcess [queue=").append(queue).append("]");
        return builder.toString();
    }

    public TemperatureUnit remove()
    {
        return queue.poll();
    }

    public boolean isEmpty()
    {
        return queue.isEmpty();
    }

    public void schedule(Collection<TemperatureUnit> scheduledUnits)
    {
        queue.addAll(scheduledUnits);
    }
    
    
}