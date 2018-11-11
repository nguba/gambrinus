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

import me.nguba.gambrinus.event.EventPublisher;
import me.nguba.gambrinus.process.ProcessValue;
import me.nguba.gambrinus.process.Segment;
import me.nguba.gambrinus.process.TemperatureProcess;
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

    private EventPublisher publisher;

    private State state;

    private SchedulerContext(final TemperatureProcess process)
    {
        this.process = process;
    }

    public void advance()
    {
        final Segment segment = process.remove();
        if (publisher != null)
            publisher.publish(SegmentComplete.on(segment));
    }

    public void await() throws InterruptedException
    {
        latch.await();
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

    public boolean hasSetpointReached()
    {
        // TODO null checks for current unit and pv
        return process.current().hasSetpointReached(processValue);
    }

    public boolean isSegmentComplete()
    {
        return process.current().isComplete();
    }

    public void setProcessValue(final ProcessValue processValue)
    {
        this.processValue = processValue;

        // send event to signal interested parties that the temperture reading changed
        if (publisher != null)
            publisher.publish(ProcessValueChanged.on(state, process.current(), processValue));
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
        builder.append("SchedulerContext [");
        if (process != null)
            builder.append("process=").append(process).append(", ");
        if (processValue != null)
            builder.append("processValue=").append(processValue).append(", ");
        if (state != null)
            builder.append("state=").append(state);
        builder.append("]");
        return builder.toString();
    }

    public SchedulerContext with(final EventPublisher publisher)
    {
        this.publisher = publisher;
        return this;
    }

}
