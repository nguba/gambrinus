package me.nguba.gambrinus.brewpi.io;

import me.nguba.gambrinus.brewpi.SparkCommand;
import me.nguba.gambrinus.io.SerialDevice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public final class CommandWriter implements Consumer<SparkCommand> {

  private static final Logger LOG = LoggerFactory.getLogger(CommandWriter.class);

  private final SerialDevice serialDevice;

  public CommandWriter(final SerialDevice serialDevice) {
    this.serialDevice = serialDevice;
  }

  @Override
  public void accept(final SparkCommand command) {

    if (command == null) {
      LOG.warn("Empty command received");
      return;
    }

    final StringBuilder builder = new StringBuilder(command.toString()).append("\n");
    serialDevice.write(builder.toString());
    LOG.debug("WRITE | {}", command);
  }
}
