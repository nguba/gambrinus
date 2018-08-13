package me.nguba.gambrinus.cqrs.command;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public interface CommandEvent
{
    default LocalDateTime timestamp()
    {
        return LocalDateTime.now();
    }
}
