package me.nguba.brauhaus.server;

import me.nguba.brauhaus.domain.process.Temperature;

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
