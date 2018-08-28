package me.nguba.gambrinus.ddd.support;

/**
 * Encapsulates common functionality for a Value objects whose identity is a single field only.
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public abstract class SingleValueObject<T>
{
  private final T value;

  protected SingleValueObject(final T value)
  {
    this.value = value;
  }

  public T getValue()
  {
    return value;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + (value == null ? 0 : value.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final SingleValueObject<?> other = (SingleValueObject<?>) obj;
    if (value == null) {
      if (other.value != null)
        return false;
    } else if (!value.equals(other.value))
      return false;
    return true;
  }

  public boolean isValid()
  {
    return value != null;
  }

  @Override
  public String toString()
  {
    return value.toString();
  }
}
