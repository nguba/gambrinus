package me.nguba.gambrinus.scheduler.state;

import me.nguba.gambrinus.process.TemperatureProcess;
import me.nguba.gambrinus.scheduler.SchedulerContext;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ExitTest
{
    final SchedulerContext context = SchedulerContext.on(TemperatureProcess.empty());

    @Test
    void callExitMultipleTimes()
    {
        context.setState(Exit.INSTANCE);

        assertFalse(context.hasAvailable());

        for (int i = 0; i < 300; i++) {
            context.handle();
            assertFalse(context.hasAvailable());
        }
    }

    @Test
    void contextCanHandleNullStateOnTermination()
    {
        context.handle();
    }

}
