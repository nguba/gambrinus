package me.nguba.gambrinus.io;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

class SerialStubTest {

  private static final ByteBuffer MSG    = ByteBuffer.wrap("message\n".getBytes());
  private final SerialStub        serial = new SerialStub();

  @Test
  void reportsCorrectNumberOfAvailableBytes() {
    final int length = serial.write(MSG);

    assertThat(serial.available()).isEqualTo(length);
  }

  @Test
  void reportsCorrectNumberOfAvailableBytesOnNullMessage() {
    final ByteBuffer msg = null;
    final int length = serial.write(msg);

    assertThat(serial.available()).isEqualTo(length);
  }

  @Test
  void reportsCorrectNumberOfAvailableBytesOnEmptyMessage() {
    final int length = serial.write(ByteBuffer.wrap("".getBytes()));

    assertThat(serial.available()).isEqualTo(length);
  }

  @Test
  void doesNotAddNullMessage() {
    final ByteBuffer msg = null;
    serial.write(msg);

    assertThat(serial.available()).isZero();
  }

  @Test
  void reportsZeroAvailableOnEmptyString() {
    serial.write(ByteBuffer.wrap("".getBytes()));

    assertThat(serial.available()).isZero();
  }

  @Test
  void writesMultipleMessages() {
    int length = 0;

    length += serial.write(ByteBuffer.wrap("hello".getBytes()));
    length += serial.write(ByteBuffer.wrap(", ".getBytes()));
    length += serial.write(ByteBuffer.wrap("world!".getBytes()));

    assertThat(serial.available()).isEqualTo(length);
  }

  @Test
  void hasAvailableWhenBufferHasMessages() {
    serial.write(ByteBuffer.wrap("message\n".getBytes()));

    assertThat(serial.hasAvailable()).isTrue();
  }

  @Test
  void hasNoneAvailableWhenBufferEmpty() {

    assertThat(serial.hasAvailable()).isFalse();
  }

  @Test
  void readReturnsZeroWhenBufferEmpty() throws Exception {

    assertThat(serial.read(ByteBuffer.allocate(0))).isZero();
  }

  @Test
  void readsPartialWritesAsFullMessage() throws Exception {
    final ByteBuffer message = ByteBuffer.wrap("Hello Pi!".getBytes());
    while (message.hasRemaining()) {
      final ByteBuffer out = ByteBuffer.allocate(1);
      out.put(message.get());
      serial.write(out);
    }

    final ByteBuffer actual = ByteBuffer.allocate(message.capacity());

    while (serial.hasAvailable()) {
      final ByteBuffer in = ByteBuffer.allocate(serial.available());
      serial.read(in);
      actual.put(in);
    }

    actual.flip();
    assertThat(actual).isEqualTo(message);
  }

  @Test
  void writeChuncked_returnsCountOfTotalBytesWritten() {
    final int written = writeChunked("123456789");

    assertThat(written).isEqualTo("123456789".length());
  }

  /**
   * @param message
   * @return
   */
  private int writeChunked(final String message) {
    final ByteBuffer buf = ByteBuffer.wrap(message.getBytes());

    final int written = serial.writeChunked(buf);
    return written;
  }

  @Test
  void readChunkedMessageReturnsByteCountOfEntireMessage() {
    final int written = writeChunked("123456789");

    final ByteBuffer in = ByteBuffer.allocate(serial.available());
    int read = 0;
    while (serial.hasAvailable()) {
      read += serial.read(in);
    }

    assertThat(read).isEqualTo(written);
  }

  @Test
  void readChunkedMessageInChunks() {
    final int written = writeChunked("123456789");

    int read = 0;
    final ByteBuffer in = ByteBuffer.allocate(serial.available() - 1);
    read += serial.read(in);

    final ByteBuffer in2 = ByteBuffer.allocate(serial.available());
    read += serial.read(in2);

    assertThat(read).isEqualTo(written);
  }
}
