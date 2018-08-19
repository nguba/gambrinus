package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.GuavaEventPublisher;
import me.nguba.gambrinus.cqrs.command.EventPublisher;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwfsMonitorTest
{
    private OwfsMonitor monitor ;
    
    private final EventPublisher publisher = new GuavaEventPublisher();
    
    @BeforeEach
    void setUp()
    {
        monitor =  new OwfsMonitor(publisher);
    }
    
    @Test
    void broadcastTemperature()
    {
        
    }

}
