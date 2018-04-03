package me.nguba.gambrinus.hardware;

import me.nguba.gambrinus.domain.Service;
import me.nguba.gambrinus.process.Event;
import me.nguba.gambrinus.process.Temperature;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.ReplayProcessor;

import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Broadcasts temperature readings to registered devices.
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class TemperatureService implements Service {

  private static final Logger LOG = LoggerFactory.getLogger(TemperatureService.class);

  private final ReplayProcessor<Event<Temperature>> processor = ReplayProcessor.create();

  public void subscribe(final TemperatureObserver observer) {
    processor.subscribe(new BaseSubscriber<Event<Temperature>>() {

      @Override
      protected void hookOnSubscribe(final Subscription subscription) {
        requestUnbounded();
      }

      @Override
      protected void hookOnNext(final Event<Temperature> event) {
        LOG.debug("Received {}", event);
        observer.onEvent(event);
      }
    });
  }

  public void publish(final Event<Temperature> event) {
    processor.onNext(event);
  }

}