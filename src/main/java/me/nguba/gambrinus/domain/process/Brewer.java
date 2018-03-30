package me.nguba.gambrinus.domain.process;

import me.nguba.gambrinus.domain.Entity;
import me.nguba.gambrinus.domain.hardware.Vessel;

import java.util.UUID;

public class Brewer implements Entity<UUID> {

  private final UUID id;

  Brewer(UUID id) {
    this.id = id;
  }

  @Override
  public UUID id() {
    return null;
  }

//  public void startMash(Schedule schedule, Vessel vessel) {
//
//  }
//
//  public void assignMashTun(Vessel vessel) {
//
//  }
//
//  public void assignBoilKettle(Vessel vessel) {
//
//  }
//
//  public void assignHotLiquorTank(Vessel vessel) {
//
//  }
}
