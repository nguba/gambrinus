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

package me.nguba.gambrinus.scheduler;

import me.nguba.gambrinus.process.ProcessValue;
import me.nguba.gambrinus.process.Temperature;
import me.nguba.gambrinus.scheduler.event.SegmentStateChanged;
import me.nguba.gambrinus.scheduler.state.Exit;
import me.nguba.gambrinus.scheduler.state.Ramp;
import me.nguba.gambrinus.scheduler.state.State;

import com.google.common.eventbus.Subscribe;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class MockProcessValueSource implements ProcessValueSource
{
    public static MockProcessValueSource instance()
    {
        return new MockProcessValueSource();
    }

    ProcessValue pv = ProcessValue.with(Temperature.celsius(45));

    State state = Exit.INSTANCE;

    @Subscribe
    public void onDomainEvent(final SegmentStateChanged event)
    {
        state = event.getState();
    }

    @Override
    public ProcessValue read()
    {
        if (Ramp.INSTANCE.equals(state))
            pv = pv.increment(Temperature.celsius(1));
        return pv;
    }
} 
