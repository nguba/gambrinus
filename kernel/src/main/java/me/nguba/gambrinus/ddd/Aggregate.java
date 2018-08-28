package me.nguba.gambrinus.ddd;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 * @param <I>
 */
public abstract class Aggregate<I> extends Entity<I>
{
  protected Aggregate(final I id)
  {
    super(id);
  }
}
