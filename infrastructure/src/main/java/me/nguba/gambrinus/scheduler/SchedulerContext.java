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
import me.nguba.gambrinus.process.TemperatureProcess;
import me.nguba.gambrinus.process.TemperatureUnit;
import me.nguba.gambrinus.scheduler.state.Exit;
import me.nguba.gambrinus.scheduler.state.Load;
import me.nguba.gambrinus.scheduler.state.State;

import java.util.concurrent.CountDownLatch;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class SchedulerContext
{
    public static SchedulerContext on(final TemperatureProcess process)
    {
        final SchedulerContext schedulerContext = new SchedulerContext(process);
        schedulerContext.setState(Load.INSTANCE);
        return schedulerContext;
    }

    private final CountDownLatch latch = new CountDownLatch(1);

    private final TemperatureProcess process;

    private ProcessValue processValue;

    private State state;

    private SchedulerContext(final TemperatureProcess process)
    {
        this.process = process;
    }

    public void advance()
    {
        process.remove();
    }

    public void await() throws InterruptedException
    {
        latch.await();
    }

    public TemperatureUnit currentUnit()
    {
        return process.currentUnit();
    }

    public TemperatureProcess getProcess()
    {
        return process;
    }

    public ProcessValue getProcessValue()
    {
        return processValue;
    }

    public State getState()
    {
        return state;
    }

    public void handle()
    {
        if (state != null)
            state.handle(this);
        else state = Exit.INSTANCE;
    }

    public boolean hasAvailable()
    {
        return !process.isEmpty();
    }

    public void setProcessValue(final ProcessValue processValue)
    {
        this.processValue = processValue;
    }

    public void setState(final State state)
    {
        this.state = state;
    }

    public void terminate()
    {
        latch.countDown();
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("SchedulerContext [currentState=").append(state).append(", process=")
                .append(process).append("]");
        return builder.toString();
    }

}
