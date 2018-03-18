package me.nguba.gambrinus.domain.hardware;

import me.nguba.gambrinus.EventListener;
import me.nguba.gambrinus.domain.process.Event;
import me.nguba.gambrinus.domain.process.Temperature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class TemperatureSensor
    implements Sensor<OneWireAddress, Temperature>, EventListener<Temperature> {

  private static final Logger LOG = LoggerFactory.getLogger(TemperatureSensor.class);

  private final Sensor<OneWireAddress, Temperature> delegate;

  private final OneWireAddress id;

  private TemperatureSensor(final Sensor<OneWireAddress, Temperature> delegate) {
    this.delegate = delegate;
    id = delegate.id();
  }

  public static TemperatureSensor sensor(final Sensor<OneWireAddress, Temperature> delegate) {
    return new TemperatureSensor(delegate);
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
    builder.append("TemperatureSensor [delegate=").append(delegate).append("]");
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
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    TemperatureSensor other = (TemperatureSensor) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
