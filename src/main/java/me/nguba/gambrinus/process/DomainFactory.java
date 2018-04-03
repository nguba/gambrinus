package me.nguba.gambrinus.process;

import java.util.List;

public final class DomainFactory {

  public Schedule makeSchedule(final String name, final List<Step> steps) {
    return new Schedule(name, steps);
  }
}
