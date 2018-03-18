package me.nguba.gambrinus.domain.process.exception;

import me.nguba.gambrinus.domain.process.Step;

public final class DuplicateStep extends Exception {

  private static final long serialVersionUID = 3569240042035993461L;

  public DuplicateStep(final Step step) {
    super(String.format("Cannot add duplicate step: %s", step.name()));
  }

}
