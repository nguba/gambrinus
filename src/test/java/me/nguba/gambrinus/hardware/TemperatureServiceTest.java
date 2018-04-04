package me.nguba.gambrinus.hardware;

import me.nguba.gambrinus.process.Event;
import me.nguba.gambrinus.process.Temperature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.Test;

class TemperatureServiceTest {

  private static final Logger LOG = LoggerFactory.getLogger(TemperatureServiceTest.class);

  private final TemperatureService bus = new TemperatureService();

  private static class A implements TemperatureObserver {

    @Override
    public void onEvent(final Event<Temperature> event) {
      LOG.info("A -> {}", event);
    }
  }

  private static class B implements TemperatureObserver {

    @Override
    public void onEvent(final Event<Temperature> event) {
      LOG.info("B -> {}", event);
    }
  }

  @Test
  void test() {
    bus.subscribe(new A());
    bus.subscribe(new B());

    bus.publish(Event.valueOf(Temperature.valueOf(18.5)));
  }
}
