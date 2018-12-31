package me.nguba.gambrinus.scheduler.state;

import me.nguba.gambrinus.GuavaEventPublisher;
import me.nguba.gambrinus.process.ProcessValue;
import me.nguba.gambrinus.process.Temperature;
import me.nguba.gambrinus.scheduler.SegmentContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoadTest
{
    SegmentContext context = SegmentContext.with(GuavaEventPublisher.create());

    @BeforeEach
    void beforeEach()
    {
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
        enqueue();

        setProcessValue(Temperature.celsius(25.0));

        context.handle();

        assertEquals(Ramp.INSTANCE, context.getState());
    }

    private void enqueue()
    {
        context.enqueue(ProcessMother.segment50());
    }

    @Test
    public void returnsSoakWhenAboveSetpoint()
    {
        enqueue();
        
        setProcessValue(Temperature.celsius(100));

        context.handle();

        assertEquals(Soak.INSTANCE, context.getState());
    }

    @Test
    public void returnsSoakWhenOnSetpoint()
    {
        enqueue();

        setProcessValue(Temperature.celsius(50.0));

        context.handle();

        assertEquals(Soak.INSTANCE, context.getState());
    }

    /**
     * @param temperature
     */
    private void setProcessValue(Temperature temperature)
    {
        context.setProcessValue(ProcessValue.with(temperature));
    }
}
