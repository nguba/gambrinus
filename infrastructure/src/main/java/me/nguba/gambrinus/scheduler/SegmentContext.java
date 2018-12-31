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

import me.nguba.gambrinus.event.DomainEvent;
import me.nguba.gambrinus.event.EventPublisher;
import me.nguba.gambrinus.process.ProcessValue;
import me.nguba.gambrinus.process.Segment;
import me.nguba.gambrinus.scheduler.event.ProcessValueChanged;
import me.nguba.gambrinus.scheduler.event.SegmentComplete;
import me.nguba.gambrinus.scheduler.event.SegmentStateChanged;
import me.nguba.gambrinus.scheduler.event.SetpointChanged;
import me.nguba.gambrinus.scheduler.state.Exit;
import me.nguba.gambrinus.scheduler.state.Load;
import me.nguba.gambrinus.scheduler.state.State;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class SegmentContext
{
    private final Queue<Segment> queue  = new ConcurrentLinkedQueue<Segment>();

    public static SegmentContext with(EventPublisher publisher, Segment...segments)
    {
        final SegmentContext schedulerContext = new SegmentContext(publisher);
        schedulerContext.setState(Load.INSTANCE);
        schedulerContext.queue.addAll(Arrays.asList(segments));
        return schedulerContext;
    }

    private final CountDownLatch latch = new CountDownLatch(1);

    private ProcessValue processValue;

    private EventPublisher publisher;

    private State state;

    private SegmentContext(final EventPublisher publisher)
    {
        this.publisher = publisher;
    }

    public boolean enqueue(Segment e)
    {
        return queue.add(e);
    }

    public void advance()
    {
        final Segment segment = queue.remove();

        publish(SegmentComplete.on(segment));
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

        else setState(Exit.INSTANCE);
    }

    public boolean hasAvailable()
    {
        return !queue.isEmpty();
    }

    public boolean hasSetpointReached()
    {
        // TODO null checks for current unit and pv
        return queue.element().hasSetpointReached(processValue);
    }

    public boolean isSegmentComplete()
    {
        return queue.element().isComplete();
    }

    public void broadcastSetpoint()
    {
        if (hasAvailable()) {
            publish(SetpointChanged.on(queue.element()));
        }
    }

    private void publish(final DomainEvent event)
    {
        if (publisher != null)
            publisher.publish(event);
    }

    public void setProcessValue(final ProcessValue processValue)
    {
        this.processValue = processValue;

        // send event to signal interested parties that the temperture reading changed
        publish(ProcessValueChanged.on(state, queue.element(), processValue));
    }

    public void setState(final State state)
    {
        this.state = state;

        publish(SegmentStateChanged.on(state));
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
        if (processValue != null)
            builder.append("processValue=").append(processValue).append(", ");
        if (state != null)
            builder.append("state=").append(state);
        builder.append("]");
        return builder.toString();
    }


}
