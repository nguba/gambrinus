package me.nguba.gambrinus;

import java.time.Instant;

import me.nguba.gambrinus.event.MutatorEvent;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class CommandHappenedEvent extends MutatorEvent
{

  public CommandHappenedEvent()
  {
    super(Instant.now());
  }

}
