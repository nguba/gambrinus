package me.nguba.gambrinus.cqrs.command;

import me.nguba.gambrinus.ddd.validation.Errors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicBoolean;

class CommandProcessorTest
        implements CommandMutator<CommandProcessorTest, CommandProcessorTest>, Command,
        EventPublisher, CommandEvent
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
    void validator() throws Exception
    {
        register();

        assertFalse(validated.get());

        processor.execute(this);

        assertTrue(validated.get());
    }

    @Test
    @DisplayName("Publishes event with command as entity")
    void published() throws Exception
    {
        register();

        processor.execute(this);

        assertTrue(published.get());
    }

    @Override
    public <E extends CommandEvent> void publish(E onCompletion)
    {
    }

    @Override
    public CommandProcessorTest onCompletion(CommandProcessorTest command)
    {
        published.getAndSet(true);
        return this;
    }

    @Override
    public void validate(CommandProcessorTest command, Errors errors)
    {
        validated.getAndSet(true);
    }

}
