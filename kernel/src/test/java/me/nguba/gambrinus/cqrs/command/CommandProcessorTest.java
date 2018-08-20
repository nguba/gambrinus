package me.nguba.gambrinus.cqrs.command;

import me.nguba.gambrinus.ddd.validation.Errors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicBoolean;

class CommandProcessorTest
        implements Command, EventPublisher, MutatorEvent, MutatorFactory,
        Mutator<CommandProcessorTest>
{
    private final CommandProcessor processor = new CommandProcessor(this);

    private final AtomicBoolean executed = new AtomicBoolean();

    private final AtomicBoolean validated = new AtomicBoolean();

    private final AtomicBoolean published = new AtomicBoolean();

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
    void execute() throws Exception
    {
        register();

        assertFalse(executed.get());

        processor.process(this);

        assertTrue(executed.get());
    }

    @Override
    public void mutate(final CommandProcessorTest command)
    {
        executed.getAndSet(true);
    }

    @Test
    @DisplayName("Executes validator when requested")
    void validator() throws Exception
    {
        register();

        assertFalse(validated.get());

        processor.process(this);

        assertTrue(validated.get());
    }

    @Test
    @DisplayName("Publishes event with command as entity")
    void published() throws Exception
    {
        register();

        processor.process(this);

        assertTrue(published.get());
    }

    @Override
    public <E extends MutatorEvent> void publish(final E onCompletion)
    {
    }

    @Override
    public CommandProcessorTest onCompletion(final CommandProcessorTest command)
    {
        published.getAndSet(true);
        return this;
    }

    @Override
    public void validate(final CommandProcessorTest command, final Errors errors)
    {
        validated.getAndSet(true);
    }

    @Override
    public void subscribe(final Object recipient)
    {
    }

    @Override
    public CommandProcessorTest make()
    {
        return this;
    }
}
