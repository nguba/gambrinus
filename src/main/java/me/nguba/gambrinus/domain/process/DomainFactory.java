package me.nguba.gambrinus.domain.process;

import java.util.List;

public final class DomainFactory {
  
  public Schedule makeSchedule(String name, List<Step> steps) {
    return new Schedule(name, steps);
  }
}
