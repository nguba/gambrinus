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

package me.nguba.gambrinus.scheduler.state;

import me.nguba.gambrinus.process.ProcessValue;
import me.nguba.gambrinus.process.Temperature;
import me.nguba.gambrinus.process.TemperatureProcess;
import me.nguba.gambrinus.scheduler.SchedulerContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class RampTest
{
    SchedulerContext         context;
    final TemperatureProcess process = TemperatureProcess.empty();

    @BeforeEach
    void beforeEach()
    {
        context = SchedulerContext.on(process);
        context.setState(Ramp.INSTANCE);
    }

    @Test
    void shouldStayAtSameStateWhenBelowSetpoint()
    {
        process.schedule(ProcessMother.firstUnit());

        context.setProcessValue(ProcessValue.with(Temperature.celsius(40.0)));

        context.handle();

        assertEquals(Ramp.INSTANCE, context.getState());
    }

    @Test
    void shouldTransitionToSoakOnSetpointReached()
    {
        process.schedule(ProcessMother.firstUnit());

        context.setProcessValue(ProcessValue.with(Temperature.celsius(50.0)));

        context.handle();

        assertEquals(Soak.INSTANCE, context.getState());
    }

}
