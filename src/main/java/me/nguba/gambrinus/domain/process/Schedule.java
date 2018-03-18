package me.nguba.gambrinus.domain.process;

import me.nguba.gambrinus.domain.Entity;
import me.nguba.gambrinus.domain.process.exception.DuplicateStep;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Schedule implements Entity<UUID> {

  private final String name;

  private final Set<Step> steps = new LinkedHashSet<>();

  private final UUID id;

  public void add(final Step step) throws DuplicateStep {
    if (steps.contains(step)) {
      throw new DuplicateStep(step);
    }
    steps.add(step);
  }

  private Schedule(final UUID id, final String name) {
    this.id = id;
    this.name = name;
  }

  public static final Schedule make(final UUID id, final String name) {
    return new Schedule(id, name);
  }

  @Override
  public UUID id() {
    return id;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("Schedule [id=").append(id).append(", name=").append(name).append(", steps=")
        .append(steps).append("]");
    return builder.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
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
    final Schedule other = (Schedule) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

  public List<Step> steps() {
    return new ArrayList<Step>(steps);
  }

}
