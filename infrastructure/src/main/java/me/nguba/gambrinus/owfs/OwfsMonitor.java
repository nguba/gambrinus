package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.cqrs.command.EventPublisher;

/**
 * 
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class OwfsMonitor
{
    private EventPublisher publisher;

    public OwfsMonitor(EventPublisher publisher)
    {
        this.publisher = publisher;
    }
    
}
