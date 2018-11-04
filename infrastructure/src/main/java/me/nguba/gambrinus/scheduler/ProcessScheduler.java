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

    public static void run(final TemperatureProcess process, final ProcessValueProvider pvProvider)
            throws Exception
    {
        runAtRate(process, pvProvider, Duration.ofSeconds(2));
    }

    public static void runAtRate(final TemperatureProcess process,
                                 final ProcessValueProvider pvProvider,
                                 Duration rate)
    {
        new ProcessScheduler(process, pvProvider).run(rate);
    }

    private final TemperatureProcess process;

    private final ProcessValueProvider pvProvider;

    private ProcessScheduler(final TemperatureProcess process,
                             final ProcessValueProvider pvProvider)
    {
        this.process = process;
        this.pvProvider = pvProvider;

    }

    public void run(Duration rate)
    {
        final SchedulerContext ctx = SchedulerContext.on(process);

        final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.initialize();
        scheduler.setDaemon(true);

        scheduler.scheduleAtFixedRate(() -> {
            try {
                ctx.setProcessValue(pvProvider.read());
                LOGGER.info("{} -> {}", ctx.getState(), ctx.currentUnit());
                ctx.handle();
            } catch (Throwable t) {
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
