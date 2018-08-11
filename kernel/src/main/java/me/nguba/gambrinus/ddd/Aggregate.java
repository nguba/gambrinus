package me.nguba.gambrinus.ddd;

public abstract class Aggregate<I> extends Entity<I>
{
    protected Aggregate(I id)
    {
        super(id);
    }
}
