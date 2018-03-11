package me.nguba.gambrinus.domain.process;

import java.time.Duration;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Step {

  private final Temperature target;

  private final Duration duration;

  private Step(final Temperature target, final Duration duration) {
    this.target = target;
    this.duration = duration;
  }

  public static Step valueOf(final Temperature target, final Duration duration) {
    return new Step(target, duration);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((duration == null) ? 0 : duration.hashCode());
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
    builder.append("Step [target=").append(target).append(", interval=").append(duration)
        .append("]");
    return builder.toString();
  }
}
