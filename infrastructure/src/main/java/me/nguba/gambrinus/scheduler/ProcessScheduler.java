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
import me.nguba.gambrinus.process.TemperatureProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.Duration;

/**
 * Provides the heartbeat with which temperatures are read and the states of a process are advanced.
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ProcessScheduler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessScheduler.class);

    public static ProcessScheduler with(final TemperatureProcess process,
                                        final ProcessValueSource pvSource)
            throws Exception
    {
        return new ProcessScheduler(process, pvSource);
    }

    private final TemperatureProcess process;

    private EventPublisher publisher;

    private final ProcessValueSource pvSource;

    private Duration rate = Duration.ofSeconds(10);

    private ProcessScheduler(final TemperatureProcess process, final ProcessValueSource pvProvider)
    {
        this.process = process;
        pvSource = pvProvider;
    }

    public ProcessScheduler publisher(final EventPublisher publisher)
    {
        this.publisher = publisher;
        return this;
    }

    public ProcessScheduler rate(final Duration rate)
    {
        this.rate = rate;
        return this;
    }

    public void run()
    {
        final SchedulerContext ctx = SchedulerContext.on(process).with(publisher);

        final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.initialize();
        scheduler.setDaemon(true);

        scheduler.scheduleAtFixedRate(() -> {
            try {
                ctx.setProcessValue(pvSource.read());
                LOGGER.info("{} -> {}", ctx.getState(), ctx.currentUnit());
                ctx.handle();
            } catch (final Throwable t) {
                LOGGER.error("Aborting scheduler run", t);
                ctx.terminate();
            }
        }, rate);

        try {
            ctx.await();
        } catch (@SuppressWarnings("unused") final InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            scheduler.destroy();
        }
    }

}
