package me.nguba.gambrinus.hardware;

import me.nguba.gambrinus.process.Event;
import me.nguba.gambrinus.process.Temperature;

public interface TemperatureSensor<K> extends Sensor<K, Temperature> {

  void onEvent(final Event<Temperature> event);

}
