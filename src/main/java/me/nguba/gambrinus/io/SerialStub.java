package me.nguba.gambrinus.io;

import me.nguba.gambrinus.brewpi.domain.AvailableDevices;
import me.nguba.gambrinus.brewpi.serialization.SparkSerializerService;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
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

  private final BlockingQueue<ByteBuffer> bytes = new ArrayBlockingQueue<ByteBuffer>(10);

  private final AtomicInteger available = new AtomicInteger();
  
  private final SparkSerializerService serializer = new SparkSerializerService();
  
  public int write(AvailableDevices devices) throws IOException {
    return write(serializer.toJson(devices));
  }
  
  public int write(final String string) {
    if (string != null && !string.isEmpty()) {
      final ByteBuffer buf = ByteBuffer.wrap(string.getBytes());
      bytes.add(buf);
      available.addAndGet(buf.remaining());
      return buf.remaining();
    }
    return 0;
  }

  @Override
  public int available() {
    return available.get();
  }

  @Override
  public int read(final byte[] buffer, final int bytesToRead) throws InterruptedException {
    ByteBuffer buf = bytes.poll();
    System.out.println(buf);
    if(buf != null) {
      while(buf.hasRemaining()) {
        System.out.print((char)buf.get());
      }
    }
    return 0;
  }

  @Override
  public boolean hasAvailable() {
    return available() > 0;
  }
}
