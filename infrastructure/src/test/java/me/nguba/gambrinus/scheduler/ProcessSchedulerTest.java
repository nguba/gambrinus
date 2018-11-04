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
import me.nguba.gambrinus.process.TemperatureProcess;
import me.nguba.gambrinus.scheduler.state.ProcessMother;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class ProcessSchedulerTest implements ProcessValueProvider
{
    TemperatureProcess process = TemperatureProcess.empty();

    ProcessValue pv = ProcessValue.zeroCelsius();

    AtomicInteger ramp = new AtomicInteger();

    @Test
    void handleEmptyProcess() throws Exception
    {
        run();
    }

    @Test
    void handleSingleUnit() throws Exception
    {
        process.schedule(ProcessMother.firstUnit());

        run();
    }

    @Test
    void abortOnError() throws Exception
    {
        process.schedule(ProcessMother.firstUnit());

        ProcessScheduler.run(process, () -> {
            return null;
        });

    }

    @Override
    public ProcessValue read()
    {
        pv = pv.increment(Temperature.celsius(ramp.getAndIncrement()));
        return pv;
    }

    private void run() throws Exception
    {
        ProcessScheduler.run(process, this);
    }
}
