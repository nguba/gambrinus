package me.nguba.gambrinus.scheduler.state;

import me.nguba.gambrinus.GuavaEventPublisher;
import me.nguba.gambrinus.process.ProcessValue;
import me.nguba.gambrinus.process.Segment;
import me.nguba.gambrinus.scheduler.SegmentContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class SoakTest
{

    SegmentContext         context;
   
    @BeforeEach
    void beforeEach()
    {
        context = SegmentContext.with(GuavaEventPublisher.create());
        context.setState(Soak.INSTANCE);

    }

    @Test
    void shouldRemoveCurrentUnitWhenDone()
    {
        Segment segment = ProcessMother.segment50();
        segment.expire();
        context.enqueue(segment);
        
        context.setProcessValue(ProcessValue.with(segment.setpoint().getValue()));

        context.handle();

        assertFalse(context.hasAvailable(), "current unit not removed from queue when done");
    }

}
