package me.nguba.gambrinus.ddd;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public interface Repository<I, A extends Aggregate<I>>
{
    I create(A agggregate);

    A read(I identifier);

    void update(A aggregate);

    void delete(I identifier);
}
