package me.nguba.gambrinus.brewpi.io;

import me.nguba.gambrinus.brewpi.SparkCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

public class CommandConsumer {

  private static final Logger LOG = LoggerFactory.getLogger(CommandConsumer.class);
  
  private BlockingQueue<SparkCommand> queue;
  
  public CommandConsumer(BlockingQueue<SparkCommand> queue) {
    Assert.notNull(queue, "Cannot assign consumer to a null queue");
    this.queue = queue;
  }

  public void take(Consumer<SparkCommand> consumer) {
    try {
      SparkCommand command = queue.take();
      LOG.info("TAKE '{}'", command);
      consumer.accept(command);
    } catch (@SuppressWarnings("unused") InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
