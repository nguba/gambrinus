package me.nguba.brauhaus.server;

public interface EventListener<T> {

  void onEvent(Event<T> event);

}
