package me.nguba.gambrinus.cqrs.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicBoolean;

class CommandProcessorTest
        implements CommandMutator<CommandProcessorTest>, Command, Validated, EventPublisher
{
    private final CommandProcessor processor = new CommandProcessor(this);

    private final AtomicBoolean executed = new AtomicBoolean();

    private final AtomicBoolean validated = new AtomicBoolean();

    private CommandMutatedEvent event;

    @Test
    @DisplayName("Can register a mutator")
    void registerMutator()
    {
        register();

        assertTrue(processor.supports(getClass()));
    }

    private void register()
    {
        processor.register(getClass(), this);
    }

    @Test
    @DisplayName("Returns false when mutator not supported")
    void noMutator()
    {
        assertFalse(processor.supports(getClass()));
    }

    @Test
    @DisplayName("Executes registered mutator")
    void execute()
    {
        register();

        assertFalse(executed.get());

        processor.execute(this);

        assertTrue(executed.get());
    }

    @Override
    public void mutate(final CommandProcessorTest command)
    {
        executed.getAndSet(true);
    }

    @Test
    @DisplayName("Executes validator when requested")
    void validator()
    {
        register();

        assertFalse(validated.get());

        processor.execute(this);

        assertTrue(validated.get());
    }

    @Test
    @DisplayName("Publishes event with command as entity")
    void publish()
    {
        register();

        assertNull(event);

        processor.execute(this);

        assertEquals(this, event.getEntity());
    }

    @Override
    public void validate()
    {
        validated.getAndSet(true);
    }

    @Override
    public void publish(final CommandMutatedEvent event)
    {
        this.event = event;
    }

}
