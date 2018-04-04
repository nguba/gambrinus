package me.nguba.gambrinus.process;

import me.nguba.gambrinus.domain.ValueObject;

import java.time.Duration;

import javax.annotation.Generated;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Step implements ValueObject {

  private final Temperature target;

  private final Duration duration;

  private final String name;

  @Generated("SparkTools")
  private Step(final Builder builder) {
    this.target = builder.target;
    this.duration = builder.duration;
    this.name = builder.name;
  }

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

  /**
   * Creates builder to build {@link Step}.
   *
   * @return created builder
   */
  @Generated("SparkTools")
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Builder to build {@link Step}.
   */
  @Generated("SparkTools")
  public static final class Builder {
    private Temperature target;
    private Duration    duration;
    private String      name;

    private Builder() {
    }

    public Builder withTarget(final Temperature target) {
      this.target = target;
      return this;
    }

    public Builder withDuration(final Duration duration) {
      this.duration = duration;
      return this;
    }

    public Builder withName(final String name) {
      this.name = name;
      return this;
    }

    public Step build() {
      return new Step(this);
    }
  }
}
