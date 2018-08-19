package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.command.CommandEvent;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class CommandHappenedEvent implements CommandEvent
{

    @Override
    public String toString()
    {
        return timestamp().toString();
    }

}
