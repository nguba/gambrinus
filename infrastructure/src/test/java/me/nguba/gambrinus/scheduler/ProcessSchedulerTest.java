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
import me.nguba.gambrinus.process.TemperatureProcess;
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
class ProcessSchedulerTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessSchedulerTest.class);

    private final Collection<DomainEvent> events = new LinkedList<>();

    TemperatureProcess process = TemperatureProcess.empty();

    GuavaEventPublisher publisher = GuavaEventPublisher.create();

    @Test
    void abortOnError() throws Exception
    {
        schedule(ProcessMother.firstUnit());

        ProcessScheduler.with(process, () -> {
            return null;
        }).rate(Duration.ofMillis(500)).run();
    }

    @BeforeEach
    void beforeEach()
    {
        subscribe();
    }

    @Test
    void handleEmptyProcess() throws Exception
    {
        run();
    }

    @Test
    void handleFullRun() throws Exception
    {
        schedule(ProcessMother.firstUnit());
        schedule(ProcessMother.secondUnit());
        schedule(ProcessMother.thirdUnit());

        run();
    }

    @Test
    void handleSingleUnit() throws Exception
    {
        schedule(ProcessMother.firstUnit());

        run();
    }

    @Subscribe
    public void onEvent(final SegmentComplete event)
    {
        events.add(event);
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
        schedule(ProcessMother.firstUnit());

        subscribe();

        run();

        final DomainEvent next = events.iterator().next();

        assertThat(next).isInstanceOf(ProcessValueChanged.class);
    }

    @Test
    void publishUnitCompletedEvent() throws Exception
    {
        schedule(ProcessMother.firstUnit());

        subscribe();

        run();

        assertThat(events).hasOnlyElementsOfTypes(SegmentComplete.class, ProcessValueChanged.class);
    }

    private void run() throws Exception
    {
        final MockProcessValueSource instance = MockProcessValueSource.instance();
        publisher.subscribe(instance);

        ProcessScheduler.with(process, instance).rate(Duration.ofMillis(500)).publisher(publisher)
                .run();
    }

    private void schedule(final Segment firstUnit)
    {
        process.schedule(firstUnit);
    }

    private void subscribe()
    {
        publisher.subscribe(this);
    }
}
