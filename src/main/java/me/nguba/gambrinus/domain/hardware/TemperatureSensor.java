package me.nguba.gambrinus.domain.hardware;

import me.nguba.gambrinus.domain.process.Event;
import me.nguba.gambrinus.domain.process.Temperature;

import java.util.EventListener;

public interface TemperatureSensor<K, T> extends Sensor<K, T> {

  void onEvent(final Event<Temperature> event);

}
