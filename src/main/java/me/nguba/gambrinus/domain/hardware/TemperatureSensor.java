package me.nguba.gambrinus.domain.hardware;

import me.nguba.gambrinus.Event;
import me.nguba.gambrinus.EventListener;
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

  private TemperatureSensor(final Sensor<OneWireAddress, Temperature> delegate) {
    this.delegate = delegate;
  }

  public static TemperatureSensor sensor(final Sensor<OneWireAddress, Temperature> delegate) {
    return new TemperatureSensor(delegate);
  }

  @Override
  public OneWireAddress id() {
    return delegate.id();
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
}
