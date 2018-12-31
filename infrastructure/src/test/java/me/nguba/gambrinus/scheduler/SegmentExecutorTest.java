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

import me.nguba.gambrinus.GuavaEventPublisher;
import me.nguba.gambrinus.event.DomainEvent;
import me.nguba.gambrinus.process.Segment;
import me.nguba.gambrinus.scheduler.event.ProcessValueChanged;
import me.nguba.gambrinus.scheduler.event.SegmentComplete;
import me.nguba.gambrinus.scheduler.state.ProcessMother;

import com.google.common.eventbus.Subscribe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class SegmentExecutorTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SegmentExecutorTest.class);

    private final Collection<DomainEvent> events = new LinkedList<>();

    GuavaEventPublisher publisher = GuavaEventPublisher.create();

    private SegmentExecutor scheduler;

    @Test
    void abortOnError() throws Exception
    {
        scheduler.run(ProcessMother.nullSegment());
    }

    @BeforeEach
    void beforeEach() throws Exception
    {
        publisher.subscribe(MockProcessValueSource.instance());
        
        publisher.subscribe(this);
        
        scheduler = SegmentExecutor.with(publisher).rate(Duration.ofMillis(500));
    }

    @Test
    void handleEmptyProcess() throws Exception
    {
        schedule();
    }

    @Test
    void handleFullRun() throws Exception
    {
        schedule(ProcessMother.segment50(), ProcessMother.segment60(), ProcessMother.segment70());
    }

    @Test
    void handleSingleUnit() throws Exception
    {
        schedule(ProcessMother.segment50());

    }
    
    @Subscribe
    public void onEvent(DomainEvent event) {
        System.out.println(event);
    }

    @Subscribe
    public void onEvent(final SegmentComplete event)
    {
        events.add(event);
        LOGGER.info("{}", event);
    }

    @Subscribe
    public void onProcessValue(final ProcessValueChanged event)
    {
        events.add(event);
        LOGGER.info("{}", event);
    }

    @Test
    void publishProcessValueChangedEvent() throws Exception
    {
        schedule(ProcessMother.segment50());
  
        final DomainEvent next = events.iterator().next();

        System.out.println(events);
    }

    @Test
    void publishSegmentCompletedEvent() throws Exception
    {
        schedule(ProcessMother.segment50());

        System.out.println(events);
        assertThat(events).hasOnlyElementsOfTypes(SegmentComplete.class, ProcessValueChanged.class);
    }


    private void schedule(final Segment... segments)
    {
        scheduler.run(segments);
    }
}
