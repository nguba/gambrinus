package me.nguba.gambrinus.domain.cqrs.command;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public interface MutatedEvent<T>
{
    T getEntityId();

    default LocalDateTime timestamp()
    {
        return LocalDateTime.now();
    }
}
