package me.nguba.gambrinus.brewpi;

import me.nguba.gambrinus.domain.Entity;
import me.nguba.gambrinus.domain.hardware.TemperatureSensor;
import me.nguba.gambrinus.domain.hardware.onewire.OneWireAddress;
import me.nguba.gambrinus.domain.hardware.onewire.OneWireTemperatureSensor;
import me.nguba.gambrinus.io.SerialDevice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Spark implements Entity<Integer> {

  private Integer                                       id;
  private SerialDevice                                  serialDevice;
  private Map<OneWireAddress, OneWireTemperatureSensor> sensors = new ConcurrentHashMap<>();

  public Spark(Integer id, SerialDevice serialDevice) {
    this.id = id;
    this.serialDevice = serialDevice;
  }

  @Override
  public Integer id() {
    return id;
  }

  public void pollAvailableDevices() {
  }

  public void listenForMessages() {
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Spark other = (Spark) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Spark [id=").append(id).append(", serialDevice=").append(serialDevice)
        .append("]");
    return builder.toString();
  }
}
