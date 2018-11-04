package me.nguba.gambrinus.scheduler.state;

import me.nguba.gambrinus.process.ProcessValue;
import me.nguba.gambrinus.process.TemperatureProcess;
import me.nguba.gambrinus.scheduler.SchedulerContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class SoakTest
{

    SchedulerContext         context;
    final TemperatureProcess process = TemperatureProcess.empty();

    @BeforeEach
    void beforeEach()
    {
        context = SchedulerContext.on(process);
        context.setState(Soak.INSTANCE);

    }

    @Test
    void shouldRemoveCurrentUnitWhenDone()
    {
        process.schedule(ProcessMother.firstUnit());
        process.currentUnit().expire();
        context.setProcessValue(ProcessValue.with(ProcessMother.firstUnit().setpoint().getValue()));

        context.handle();

        assertFalse(context.hasAvailable(), "current unit not removed from queue when done");
    }

}
