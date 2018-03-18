package me.nguba.gambrinus.domain.hardware;

import me.nguba.gambrinus.domain.process.Event;
import me.nguba.gambrinus.domain.process.Temperature;

public interface TemperatureObserver {

  void onEvent(Event<Temperature> event);

}
