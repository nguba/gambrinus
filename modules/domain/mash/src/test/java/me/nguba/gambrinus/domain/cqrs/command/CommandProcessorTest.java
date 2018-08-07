package me.nguba.gambrinus.domain.cqrs.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.atomic.AtomicBoolean;

class CommandProcessorTest implements CommandMutator<CommandProcessorTest>, Command, Validated
{
    private final CommandProcessor processor = new CommandProcessor();

    private final AtomicBoolean executed = new AtomicBoolean();
    
    private final AtomicBoolean validated = new AtomicBoolean();
    
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
    void noMutator() {
        assertFalse(processor.supports(getClass()));
    }
    
    @Test
    @DisplayName("Executes registered mutator")
    void execute() {
        register();
        
        assertFalse(executed.get());
        
        processor.execute(this);
        
        assertTrue(executed.get());
    }

    @Override
    public void mutate(CommandProcessorTest command)
    {
        executed.getAndSet(true);
    }

    @Test
    @DisplayName("Executes validator when requested")
    void validator() {
        register();
        
        assertFalse(validated.get());
        
        processor.execute(this);
        
        assertTrue(validated.get());
    }

    @Override
    public void validate()
    {
        validated.getAndSet(true);
    }
}
