package me.nguba.gambrinus.scheduler.state;

import me.nguba.gambrinus.process.ProcessValue;
import me.nguba.gambrinus.process.Temperature;
import me.nguba.gambrinus.process.TemperatureProcess;
import me.nguba.gambrinus.scheduler.SchedulerContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoadTest
{
    SchedulerContext         context;
    final TemperatureProcess process = TemperatureProcess.empty();

    @BeforeEach
    void beforeEach()
    {
        context = SchedulerContext.on(process);
        context.setState(Load.INSTANCE);
    }

    @Test
    public void emptyProcessReturnsExit()
    {
        context.handle();

        assertEquals(Exit.INSTANCE, context.getState());
    }

    @Test
    public void returnsRampWhenBelowSetpoint()
    {
        process.schedule(ProcessMother.firstUnit());

        context.setProcessValue(ProcessValue.with(Temperature.celsius(25.0)));

        context.handle();

        assertEquals(Ramp.INSTANCE, context.getState());
    }

    @Test
    public void returnsSoakWhenAboveSetpoint()
    {
        process.schedule(ProcessMother.firstUnit());

        context.setProcessValue(ProcessValue.with(Temperature.celsius(100)));

        context.handle();

        assertEquals(Soak.INSTANCE, context.getState());
    }

    @Test
    public void returnsSoakWhenOnSetpoint()
    {
        process.schedule(ProcessMother.firstUnit());

        context.setProcessValue(ProcessValue.with(Temperature.celsius(50.0)));

        context.handle();

        assertEquals(Soak.INSTANCE, context.getState());
    }
}
