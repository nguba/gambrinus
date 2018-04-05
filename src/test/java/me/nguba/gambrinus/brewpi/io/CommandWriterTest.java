package me.nguba.gambrinus.brewpi.io;

import me.nguba.gambrinus.brewpi.SparkCommand;
import me.nguba.gambrinus.io.SerialDevice;
import me.nguba.gambrinus.io.SerialStub;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

class CommandWriterTest {

  private final SerialDevice serial = new SerialStub();
  private CommandWriter      writer;

  @BeforeEach
  public void setUp() {
    writer = new CommandWriter(serial);
  }

  @Test
  void willIgnoreNullRequests() {
    writer.accept(null);
  }

  @Test
  void writesAcceptedCommandToSerialDevice() {
    final SparkCommand command = SparkCommand.TEMPERATURE;

    writer.accept(command);

    final ByteBuffer actual = ByteBuffer.allocate(command.toString().length() + 1);

    serial.read(actual);

    actual.flip();

    final ByteBuffer expected = ByteBuffer.wrap((command.toString() + "\n").getBytes());

    assertThat(actual).isEqualTo(expected);
  }

}
