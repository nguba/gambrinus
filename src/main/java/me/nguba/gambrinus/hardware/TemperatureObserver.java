package me.nguba.gambrinus.hardware;

import me.nguba.gambrinus.process.Event;
import me.nguba.gambrinus.process.Temperature;

public interface TemperatureObserver {

  void onEvent(Event<Temperature> event);

}
