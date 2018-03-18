package me.nguba.gambrinus.domain.hardware;

import me.nguba.gambrinus.EventListener;
import me.nguba.gambrinus.domain.hardware.TemperatureService;
import me.nguba.gambrinus.domain.process.Event;
import me.nguba.gambrinus.domain.process.Temperature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.jupiter.api.Test;

class TemperatureServiceTest {

  private static final Logger LOG = LoggerFactory.getLogger(TemperatureServiceTest.class);

  private final TemperatureService bus = new TemperatureService();

  private static class A implements EventListener<Temperature> {

    @Override
    public void onEvent(final Event<Temperature> event) {
      LOG.info("A -> {}", event);
    }
  }

  private static class B implements EventListener<Temperature> {

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
