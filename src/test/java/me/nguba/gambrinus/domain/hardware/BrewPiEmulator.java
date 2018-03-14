package me.nguba.gambrinus.domain.hardware;

import me.nguba.gambrinus.domain.process.Temperature;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Allows for unit testing behaviour which needs the output from the BrewPi spark protocol.
 * 
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class BrewPiEmulator {

  private ConcurrentMap<SensorAddress, Temperature> sensors = new ConcurrentHashMap<>();

  public void temperature(SensorAddress address, double value) {
    sensors.put(address, Temperature.valueOf(value));
  }

  public void add(SensorAddress address) {
    sensors.put(address, Temperature.valueOf(0.0));
  }

  public String read() {
    return null;
  }
}
