package me.nguba.gambrinus.domain.process;

import me.nguba.gambrinus.domain.ValueObject;

import java.time.Duration;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Step implements ValueObject {

  private final Temperature target;

  private final Duration duration;

  private final String name;

  private Step(final String name, final Temperature target, final Duration duration) {
    this.name = name;
    this.target = target;
    this.duration = duration;
  }

  public static Step valueOf(final String name, final Temperature target, final Duration duration) {
    return new Step(name, target, duration);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((duration == null) ? 0 : duration.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((target == null) ? 0 : target.hashCode());
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
    final Step other = (Step) obj;
    if (duration == null) {
      if (other.duration != null) {
        return false;
      }
    } else if (!duration.equals(other.duration)) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    if (target == null) {
      if (other.target != null) {
        return false;
      }
    } else if (!target.equals(other.target)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("Step [name=").append(name).append(", target=").append(target)
        .append(", duration=").append(duration).append("]");
    return builder.toString();
  }

  public String name() {
    return name;
  }
}
