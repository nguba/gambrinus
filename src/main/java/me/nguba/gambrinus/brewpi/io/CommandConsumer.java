package me.nguba.gambrinus.brewpi.io;

import me.nguba.gambrinus.brewpi.SparkCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

public class CommandConsumer {
  private static final Logger               LOG = LoggerFactory.getLogger(CommandConsumer.class);
  private final BlockingQueue<SparkCommand> queue;

  public CommandConsumer(final BlockingQueue<SparkCommand> queue) {
    Assert.notNull(queue, "Cannot assign consumer to a null queue");
    this.queue = queue;
  }

  public void take(final Consumer<SparkCommand> consumer) {
    try {
      final SparkCommand command = queue.take();
      consumer.accept(command);
      LOG.info("ACCEPTED '{}'", command);

    } catch (@SuppressWarnings("unused") final InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
