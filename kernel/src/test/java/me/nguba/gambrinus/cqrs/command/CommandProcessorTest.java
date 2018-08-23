package me.nguba.gambrinus.cqrs.command;

import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicBoolean;

class CommandProcessorTest
        implements Command, CommandHandler<CommandProcessorTest>
{
    private final AtomicBoolean executed = new AtomicBoolean();

    private final AtomicBoolean validated = new AtomicBoolean();

    @Test
    @DisplayName("Executes registered mutator")
    void execute() throws Exception
    {
        assertFalse(executed.get());

        process();

        assertTrue(executed.get());
    }

    @Test
    @DisplayName("Error on null mutator")
    void processNullMutator() throws Exception
    {
        assertFalse(executed.get());

        assertThrows(UnsupportedOperationException.class,
                     () -> CommandProcessor.process(this, null));
    }
    
    @Test
    @DisplayName("Error on null command")
    void processNullCommand() throws Exception
    {
        assertFalse(executed.get());

        assertThrows(UnsupportedOperationException.class,
                     () -> CommandProcessor.process(null, this));
    }

    /**
     * @throws ValidationFailed
     */
    private void process() throws ValidationFailed
    {
        CommandProcessor.process(this, this);
    }

    @Override
    public void changeStateFor(final CommandProcessorTest command)
    {
        executed.getAndSet(true);
    }

    @Test
    @DisplayName("Executes validator when requested")
    void validator() throws Exception
    {
        assertFalse(validated.get());

        process();

        assertTrue(validated.get());
    }

    @Override
    public void validate(final CommandProcessorTest command, final Errors errors)
    {
        validated.getAndSet(true);
    }
}
