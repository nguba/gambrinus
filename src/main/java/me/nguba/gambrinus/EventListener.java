package me.nguba.gambrinus;

public interface EventListener<T> {

  void onEvent(Event<T> event);

}
