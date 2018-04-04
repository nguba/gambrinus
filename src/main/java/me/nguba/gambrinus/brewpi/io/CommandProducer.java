package me.nguba.gambrinus.brewpi.io;

import me.nguba.gambrinus.brewpi.SparkCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;

public class CommandProducer {

  private static final Logger LOG = LoggerFactory.getLogger(CommandProducer.class);

  private BlockingQueue<SparkCommand> queue;

  public CommandProducer(BlockingQueue<SparkCommand> queue) {
    Assert.notNull(queue, "Cannot assign producer to a null queue");

    this.queue = queue;
  }

  public void queue(Supplier<SparkCommand> supplier) {
    SparkCommand e = supplier.get();
    try {
      queue.put(e);
      LOG.info("OFFER '{}'", e);
    } catch (@SuppressWarnings("unused") InterruptedException interrupt) {
      Thread.currentThread().interrupt();
    }

  }

}
