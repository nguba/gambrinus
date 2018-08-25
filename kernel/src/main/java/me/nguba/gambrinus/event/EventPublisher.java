package me.nguba.gambrinus.event;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public interface EventPublisher
{
  <E extends MutatorEvent> void publish(E event);

  void subscribe(Object recipient);
}
