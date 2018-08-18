package me.nguba.gambrinus.ddd;

import java.util.Optional;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public interface Repository<I, A extends Aggregate<I>>
{
    Optional<I> create(A aggregate);

    Optional<A> read(I identifier);

    void update(A aggregate);

    void delete(I identifier);
}
