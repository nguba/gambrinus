package me.nguba.gambrinus.domain.hardware;

import me.nguba.gambrinus.domain.Entity;
import me.nguba.gambrinus.domain.process.Temperature;

import org.springframework.util.Assert;

import java.util.UUID;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Vessel implements Entity<UUID> {

  private final UUID id;

  private final String label;

  private final TemperatureSensor sensor;

  @Override
  public UUID id() {
    return id;
  }

  public String label() {
    return label;
  }

  private Vessel(final UUID id, final String label, final TemperatureSensor sensor) {
    Assert.notNull(id, "Identity cannot be null");
    Assert.notNull(label, "Label cannot be null");
    Assert.notNull(sensor, "Temperature Sensor cannot be null");
    
    this.id = id;
    this.label = label;
    this.sensor = sensor;
  }

  public static Vessel make(final UUID id,
                              final String label,
                              final TemperatureSensor sensor) {
    return new Vessel(id, label, sensor);
  }
  
  public Temperature read() {
    return sensor.read();
  }

  @Override
  public String toString() {
    return label;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Vessel other = (Vessel) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }
}
