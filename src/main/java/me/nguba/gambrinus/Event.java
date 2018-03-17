package me.nguba.gambrinus;

import me.nguba.gambrinus.domain.ValueObject;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 * @param <T>
 */
public final class Event<T> implements ValueObject {

  private final T value;

  private Event(final T value) {
    this.value = value;
  }

  public static <T> Event<T> valueOf(final T value) {
    return new Event<T>(value);
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("Event [value=").append(value).append("]");
    return builder.toString();
  }

  public T value() {
    return value;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((value == null) ? 0 : value.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Event<?> other = (Event<?>) obj;
    if (value == null) {
      if (other.value != null) {
        return false;
      }
    } else if (!value.equals(other.value)) {
      return false;
    }
    return true;
  }

}
