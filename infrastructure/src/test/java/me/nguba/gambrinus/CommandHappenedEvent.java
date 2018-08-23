package me.nguba.gambrinus;

import me.nguba.gambrinus.event.MutatorEvent;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class CommandHappenedEvent implements MutatorEvent
{

    @Override
    public String toString()
    {
        return timestamp().toString();
    }

}
