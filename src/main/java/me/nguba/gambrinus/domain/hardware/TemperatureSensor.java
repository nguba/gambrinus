package me.nguba.gambrinus.domain.hardware;

import me.nguba.gambrinus.domain.process.Event;
import me.nguba.gambrinus.domain.process.Temperature;

public interface TemperatureSensor<K> extends Sensor<K, Temperature> {

  void onEvent(final Event<Temperature> event);

}
