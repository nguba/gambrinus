package me.nguba.gambrinus.domain.process;

import me.nguba.gambrinus.domain.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Schedule implements Entity<String> {

  private final List<Step> steps;

  private final String id;

  private Iterator<Step> iterator;

  public Schedule(final String name, List<Step> steps) {
    this.id = name;
    this.steps = steps;
    iterator = steps.iterator();
  }

  @Override
  public String toString() {
    return id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((steps == null) ? 0 : steps.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Schedule other = (Schedule) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (steps == null) {
      if (other.steps != null)
        return false;
    } else if (!steps.equals(other.steps))
      return false;
    return true;
  }

  public boolean hasCompleted() {
    return !iterator.hasNext();
  }

  public void reset() {
    iterator = steps.iterator();
  }

  public Step currentStep() {
    return iterator.next();
  }
  
  

  public List<Step> getSteps() {
    return new ArrayList<Step>(steps);
  }

  @Override
  public String id() {
    return id;
  }
}
