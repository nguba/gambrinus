package me.nguba.gambrinus.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Used for testing io behaviour over a serial device.
 * <p>
 * It will return a stream of bytes, potentially randomising the length of bytes made available in
 * the subsequent reads for the same buffer -- which emulates the behaviour of a serial device where
 * the message doesn't necessarily arrive complete.
 * </p>
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class SerialStub implements SerialDevice {

  private static final Logger LOG = LoggerFactory.getLogger(SerialStub.class);

  private final Random random = new Random();

  private final BlockingQueue<ByteBuffer> queue = new LinkedBlockingQueue<ByteBuffer>();

  private final AtomicInteger available = new AtomicInteger();

  public int writeChunked(final ByteBuffer buffer) {
    int written = 0;

    while (buffer.hasRemaining()) {

      final int bound = random.nextInt(buffer.remaining() + 1);

      final ByteBuffer chunck = ByteBuffer.allocate(bound);
      for (int i = 0; i < bound; i++) {
        chunck.put(buffer.get());
      }

      chunck.flip();
      final int sent = write(chunck);
      written += sent;
      LOG.info("WRITE | remaining={}, bound={}, sent={}", buffer.remaining(), bound, sent);
    }
    return written;
  }

  public int write(final String message) {
    return write(ByteBuffer.wrap(message.getBytes()));
  }

  @Override
  public int write(final ByteBuffer buffer) {
    if (buffer != null && buffer.hasRemaining()) {
      queue.add(buffer);
      available.addAndGet(buffer.remaining());
      return buffer.remaining();
    }
    return 0;
  }

  @Override
  public int available() {
    return available.get();
  }

  @Override
  public int read(final ByteBuffer buffer) {
    int read = 0;
    ByteBuffer queueBuffer = null;
    while ((queueBuffer = queue.peek()) != null && buffer.hasRemaining()) {
      while (queueBuffer.hasRemaining() && buffer.hasRemaining()) {
        final byte b = queueBuffer.get();
        buffer.put(b);
        available.decrementAndGet();
        read++;
        if (!queueBuffer.hasRemaining()) {
          queue.remove();
        }
      }
    }
    LOG.info(" READ | remaining={}, read={}", available(), read);
    return read;
  }

  @Override
  public boolean hasAvailable() {
    return available() > 0;
  }
}
