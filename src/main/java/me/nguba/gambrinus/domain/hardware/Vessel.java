package me.nguba.gambrinus.domain.hardware;

import me.nguba.gambrinus.domain.Entity;

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
    this.id = id;
    this.label = label;
    this.sensor = sensor;
  }

  public static Vessel vessel(final UUID id,
                              final String label,
                              final TemperatureSensor sensor) {
    return new Vessel(id, label, sensor);
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
