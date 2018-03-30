package me.nguba.gambrinus.domain.process;

import java.util.List;
import java.util.UUID;

public final class DomainFactory {
  
  public Brewer makeBrewer(UUID id) {
    return new Brewer(id);
  }
  
  public Schedule makeSchedule(String name, List<Step> steps) {
    return new Schedule(name, steps);
  }
}
