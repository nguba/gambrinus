package me.nguba.gambrinus.scheduler;

import me.nguba.gambrinus.GuavaEventPublisher;
import me.nguba.gambrinus.scheduler.event.SetpointChanged;
import me.nguba.gambrinus.scheduler.state.ProcessMother;

import com.google.common.eventbus.Subscribe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class SegmentContextTest
{
    SetpointChanged event;

    GuavaEventPublisher publisher = GuavaEventPublisher.create();

    SegmentContext ctx;

    @BeforeEach
    void setUp()
    {
        publisher.subscribe(this);

        ctx = SegmentContext.with(publisher);
    }

    @Test
    void broadcastSetpointFiresEvent()
    {
        ctx.enqueue(ProcessMother.segment50());

        ctx.broadcastSetpoint();

        assertNotNull(event);
    }

    @Test
    void broadcastSetpointNoSegment()
    {
        ctx.broadcastSetpoint();

        assertNull(event);
    }

    @Subscribe
    void onSetpointChanged(final SetpointChanged event)
    {
        this.event = event;
    }
}
