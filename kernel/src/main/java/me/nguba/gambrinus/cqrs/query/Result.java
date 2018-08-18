package me.nguba.gambrinus.cqrs.query;

import java.util.Optional;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public interface Result<T>
{
    Optional<T> getResult();
}
