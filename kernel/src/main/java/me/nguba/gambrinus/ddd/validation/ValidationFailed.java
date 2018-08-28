package me.nguba.gambrinus.ddd.validation;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ValidationFailed extends Exception
{
  private static final long serialVersionUID = -6165488948892216564L;

  private final Errors errors;

  public ValidationFailed(final Errors errors)
  {
    this.errors = errors;
  }

  public Errors getErrors()
  {
    return errors;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + (errors == null ? 0 : errors.hashCode());
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
    final ValidationFailed other = (ValidationFailed) obj;
    if (errors == null) {
      if (other.errors != null)
        return false;
    } else if (!errors.equals(other.errors))
      return false;
    return true;
  }

}
