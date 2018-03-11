package me.nguba.gambrinus.server;

import me.nguba.gambrinus.Bus;
import me.nguba.gambrinus.Event;
import me.nguba.gambrinus.EventListener;
import me.nguba.gambrinus.domain.process.Temperature;

import org.junit.jupiter.api.Test;

class BusTest {

  private final Bus bus = new Bus();

  private static class A implements EventListener<Temperature> {

    @Override
    public void onEvent(final Event<Temperature> event) {
      System.out.println("A -> " + event);
    }
  }

  private static class B implements EventListener<Temperature> {

    @Override
    public void onEvent(final Event<Temperature> event) {
      System.out.println("B -> " + event);
    }
  }

  @Test
  void test() {
    bus.subscribe(new A());
    bus.subscribe(new B());

    bus.publish(Event.valueOf(Temperature.valueOf(18.5)));
  }
}
