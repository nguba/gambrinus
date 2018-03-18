package me.nguba.gambrinus;

import me.nguba.gambrinus.domain.process.Event;

public interface EventListener<T> {

  void onEvent(Event<T> event);

}
