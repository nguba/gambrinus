package me.nguba.gambrinus.brewpi.io;

import me.nguba.gambrinus.brewpi.SparkCommand;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

class CommandConsumerTest {

  private final BlockingQueue<SparkCommand> queue = new ArrayBlockingQueue<SparkCommand>(1);
  private CommandConsumer                   consumer;

  @BeforeEach
  void setUp() {
    consumer = new CommandConsumer(queue);
  }

  @Test
  @DisplayName("should not instantiate with null queue as parameter")
  void shouldNotBeAbleToConstructWithNullQueue() {
    @SuppressWarnings("unused")
    final IllegalArgumentException throwable = assertThrows(IllegalArgumentException.class, () -> {
      new CommandConsumer(null);
    });

    assertAll(() -> assertThat(throwable.getMessage())
        .isEqualTo("Cannot assign consumer to a null queue"));
  }

  @Test
  @DisplayName("should take commands from the queue")
  void shouldDequeueCommand() throws Exception {

    queue.put(SparkCommand.DEVICES_NOT_INSTALLED);

    consumer.take((command) -> assertThat(command).isEqualTo(SparkCommand.DEVICES_NOT_INSTALLED));
  }

  @Test
  @DisplayName("should block interruptidly when empty")
  void shouldBlockInterrupted() throws Exception {
    queue.clear();

    final CountDownLatch threadStarted = new CountDownLatch(1);
    final Thread t = new Thread(() -> {
      threadStarted.countDown();
      consumer.take((c) -> {
      });
    });
    t.start();
    threadStarted.await();
    JunitThreadUtil.assertThreadStaysAlive(t);
    t.interrupt();
    t.join(1000);
  }
}
