package me.nguba.gambrinus.domain.hardware.onewire;

import me.nguba.gambrinus.domain.hardware.TemperatureSensor;
import me.nguba.gambrinus.domain.process.Event;
import me.nguba.gambrinus.domain.process.Temperature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class OneWireTemperatureSensor
    implements TemperatureSensor<OneWireAddress> {

  private static final Logger LOG = LoggerFactory.getLogger(OneWireTemperatureSensor.class);

  private final OneWireSensor delegate;

  private final OneWireAddress id;

  private OneWireTemperatureSensor(final OneWireSensor delegate) {
    this.delegate = delegate;
    id = delegate.id();
  }

  public static OneWireTemperatureSensor make(final OneWireSensor delegate) {
    return new OneWireTemperatureSensor(delegate);
  }

  @Override
  public OneWireAddress id() {
    return id;
  }

  @Override
  public Temperature read() {
    return delegate.read();
  }

  @Override
  public void onEvent(final Event<Temperature> event) {
    update(event.value());
  }

  @Override
  public void update(final Temperature value) {
    LOG.debug("updated tempeature to {}", value);
    delegate.update(value);
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("TemperatureSensor [id=").append(id).append(", delegate=").append(delegate)
        .append("]");
    return builder.toString();
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
    final OneWireTemperatureSensor other = (OneWireTemperatureSensor) obj;
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
