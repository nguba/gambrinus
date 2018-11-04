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
import me.nguba.gambrinus.process.ProcessValue;
import me.nguba.gambrinus.process.Temperature;
import me.nguba.gambrinus.process.TemperatureProcess;
import me.nguba.gambrinus.scheduler.state.ProcessMother;

import com.google.common.eventbus.Subscribe;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class ProcessSchedulerTest implements ProcessValueSource
{
    private final Collection<DomainEvent> events = new LinkedList<>();

    TemperatureProcess process = TemperatureProcess.empty();

    GuavaEventPublisher publisher = GuavaEventPublisher.create();

    ProcessValue pv = ProcessValue.zeroCelsius();

    AtomicInteger ramp = new AtomicInteger();

    @Test
    void abortOnError() throws Exception
    {
        process.schedule(ProcessMother.firstUnit());

        ProcessScheduler.with(process, () -> {
            return null;
        }).rate(Duration.ofMillis(500)).run();
        ;
    }

    @BeforeEach
    void beforeEach()
    {
        publisher.subscribe(this);
    }

    @Test
    void handleEmptyProcess() throws Exception
    {
        run();
    }

    @Test
    void handleFullRun() throws Exception
    {
        process.schedule(ProcessMother.firstUnit());
        process.schedule(ProcessMother.secondUnit());
        process.schedule(ProcessMother.thirdUnit());

        run();
    }

    @Test
    void handleSingleUnit() throws Exception
    {
        process.schedule(ProcessMother.firstUnit());

        run();
    }

    @Subscribe
    public void onEvent(final UnitCompleted event)
    {
        events.add(event);
    }

    @Subscribe
    public void onProcessValue(final ProcessValueChanged event)
    {
        events.add(event);
    }

    @Test
    void publishProcessValueChangedEvent() throws Exception
    {
        process.schedule(ProcessMother.firstUnit());

        publisher.subscribe(this);

        run();

        final DomainEvent next = events.iterator().next();

        assertThat(next).isInstanceOf(ProcessValueChanged.class);
    }

    @Test
    void publishUnitCompletedEvent() throws Exception
    {
        process.schedule(ProcessMother.firstUnit());

        publisher.subscribe(this);

        run();

        assertThat(events).hasOnlyElementsOfTypes(UnitCompleted.class, ProcessValueChanged.class);
    }

    @Override
    public ProcessValue read()
    {
        pv = pv.increment(Temperature.celsius(ramp.getAndIncrement()));
        return pv;
    }

    private void run() throws Exception
    {
        ProcessScheduler.with(process, this).rate(Duration.ofMillis(500)).publisher(publisher)
                .run();
    }
}
