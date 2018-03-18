package me.nguba.gambrinus.domain.hardware;

import me.nguba.gambrinus.domain.process.Temperature;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class OneWireSensor implements Sensor<OneWireAddress, Temperature> {

  private final OneWireAddress id;

  private Temperature temperature;

  private OneWireSensor(final OneWireAddress id, final Temperature temperature) {
    this.id = id;
    this.temperature = temperature;
  }

  public static OneWireSensor make(final OneWireAddress id) {
    return new OneWireSensor(id, Temperature.valueOf(0));
  }

  @Override
  public OneWireAddress id() {
    return id;
  }

  @Override
  public void update(final Temperature current) {
    this.temperature = current;
  }

  @Override
  public Temperature read() {
    return temperature;
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
    final OneWireSensor other = (OneWireSensor) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("OneWireSensor [id=").append(id).append(", temperature=").append(temperature)
        .append("]");
    return builder.toString();
  }
}
