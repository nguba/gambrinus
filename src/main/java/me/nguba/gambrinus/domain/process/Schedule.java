package me.nguba.gambrinus.domain.process;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class Schedule {

  private final List<Step> steps = new LinkedList<Step>();

  public void add(final Step step) {
    steps.add(step);
  }

}
