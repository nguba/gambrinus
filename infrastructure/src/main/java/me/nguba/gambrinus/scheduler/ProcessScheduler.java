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

/**
 * Provides the heartbeat with which temperatures are read and the states of a process are advanced.
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ProcessScheduler
{

    public static void run(final TemperatureProcess process, final ProcessValueProvider pvProvider)
            throws Exception
    {
        new ProcessScheduler(process, pvProvider).run();
    }

    private final TemperatureProcess process;

    private ProcessScheduler(final TemperatureProcess process,
                             final ProcessValueProvider pvProvider)
    {
        this.process = process;

    }

    public void run() throws Exception
    {
        final SchedulerContext ctx = SchedulerContext.with(process);

        while (ctx.hasAvailable())
            ctx.handle();
    }

}
