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

class CommandProducerTest {

  private final BlockingQueue<SparkCommand> queue = new ArrayBlockingQueue<SparkCommand>(1);

  private CommandProducer producer;

  @BeforeEach
  void setUp() {
    producer = new CommandProducer(queue);
  }

  @Test
  @DisplayName("should not instantiate with null queue as parameter")
  void shouldNotBeAbleToConstructWithNullQueue() {
    @SuppressWarnings("unused")
    final IllegalArgumentException throwable = assertThrows(IllegalArgumentException.class, () -> {
      new CommandProducer(null);
    });

    assertAll(() -> assertThat(throwable.getMessage())
        .isEqualTo("Cannot assign producer to a null queue"));
  }

  @Test
  @DisplayName("should add commands to the queue")
  void shouldQueueCommand() {

    assertThat(queue.isEmpty()).isTrue();

    producer.queue(() -> SparkCommand.RESET);

    assertAll(() -> assertThat(queue.poll()).isEqualTo(SparkCommand.RESET),
              () -> assertThat(queue.isEmpty()).isTrue());
  }

  @Test
  @DisplayName("should block interruptidly when full")
  void shouldBlockInterrupted() throws Exception {
    queue.offer(SparkCommand.RESET);

    final CountDownLatch threadStarted = new CountDownLatch(1);
    final Thread t = new Thread(() -> {
      threadStarted.countDown();
      producer.queue(() -> SparkCommand.RESET);
    });
    t.start();
    threadStarted.await();
    JunitThreadUtil.assertThreadStaysAlive(t);
    t.interrupt();
    t.join(1000);
  }

}
