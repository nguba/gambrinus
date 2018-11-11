package me.nguba.gambrinus.scheduler;

import me.nguba.gambrinus.GuavaEventPublisher;
import me.nguba.gambrinus.process.TemperatureProcess;
import me.nguba.gambrinus.scheduler.event.SetpointChanged;
import me.nguba.gambrinus.scheduler.state.ProcessMother;

import com.google.common.eventbus.Subscribe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SchedulerContextTest
{
    SetpointChanged event;

    TemperatureProcess process = TemperatureProcess.empty();

    @Test
    void loadSetpoint()
    {
        process.schedule(ProcessMother.firstUnit());
        final SchedulerContext ctx = SchedulerContext.on(process);

        ctx.loadSetpoint();

        assertEquals(ProcessMother.firstUnit().setpoint(), ctx.getSetpoint());
    }

    @Test
    void loadSetpointFiresEvent()
    {
        process.schedule(ProcessMother.firstUnit());

        final GuavaEventPublisher publisher = GuavaEventPublisher.create();
        publisher.subscribe(this);

        final SchedulerContext ctx = SchedulerContext.on(process).with(publisher);

        ctx.loadSetpoint();

        assertNotNull(event);
    }

    @Test
    void loadSetpointNoSegment()
    {
        final SchedulerContext ctx = SchedulerContext.on(process);

        assertThrows(IllegalStateException.class, () -> ctx.loadSetpoint());
    }

    @Test
    void loadSetpointNoSegmentTeminates()
    {
        final SchedulerContext ctx = SchedulerContext.on(process);

        assertThrows(IllegalStateException.class, () -> ctx.loadSetpoint());

        assertTrue(ctx.isTerminated());
    }

    @Subscribe
    void onSetpointChanged(final SetpointChanged event)
    {
        this.event = event;
    }
}
