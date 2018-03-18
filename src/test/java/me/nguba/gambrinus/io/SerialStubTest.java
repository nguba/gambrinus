package me.nguba.gambrinus.io;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SerialStubTest {

  private final SerialStub serial = new SerialStub();

  @Test
  void reportsCorrectNumberOfAvailableBytes() {
    final int length = serial.write("message\n");

    assertThat(serial.available()).isEqualTo(length);
  }

  @Test
  void reportsCorrectNumberOfAvailableBytesOnNullMessage() {
    final int length = serial.write(null);

    assertThat(serial.available()).isEqualTo(length);
  }

  @Test
  void reportsCorrectNumberOfAvailableBytesOnEmptyMessage() {
    final int length = serial.write("");

    assertThat(serial.available()).isEqualTo(length);
  }

  @Test
  void doesNotAddNullMessage() {

    serial.write(null);

    assertThat(serial.available()).isZero();
  }

  @Test
  void reportsZeroAvailableOnEmptyString() {
    serial.write("");

    assertThat(serial.available()).isZero();
  }

  @Test
  void writesMultipleMessages() {
    int lenght = 0;

    lenght += serial.write("hello");
    lenght += serial.write(", ");
    lenght += serial.write("world!");
    lenght += serial.write("\n");

    assertThat(serial.available()).isEqualTo(lenght);
  }

  @Test
  void hasAvailableWhenBufferHasMessages() {
    serial.write("message\n");

    assertThat(serial.hasAvailable()).isTrue();
  }

  @Test
  void hasNoneAvailableWhenBufferEmpty() {

    assertThat(serial.hasAvailable()).isFalse();
  }

  @Test
  void readReturnsZeroWhenBufferEmpty() throws Exception {

    assertThat(serial.read(null, 10)).isZero();
  }
}
