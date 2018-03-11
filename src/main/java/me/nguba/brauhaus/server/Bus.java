package me.nguba.brauhaus.server;

import me.nguba.brauhaus.domain.process.Temperature;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.ReplayProcessor;

import org.reactivestreams.Processor;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Bus {

  private static final Logger LOG = LoggerFactory.getLogger(Bus.class);

  private final ReplayProcessor<Event<Temperature>> processor = ReplayProcessor.create();

  public void subscribe(final EventListener<Temperature> listener) {
    processor.subscribe(new BaseSubscriber<Event<Temperature>>() {

      @Override
      protected void hookOnSubscribe(final Subscription subscription) {
        requestUnbounded();
      }

      @Override
      protected void hookOnNext(final Event<Temperature> event) {
        LOG.debug("Received {}", event);
        listener.onEvent(event);
      }
    });
  }

  Processor<Event<Temperature>, Event<Temperature>> processor() {
    return processor;
  }

  public void publish(final Event<Temperature> event) {
    processor.onNext(event);
  }

}
