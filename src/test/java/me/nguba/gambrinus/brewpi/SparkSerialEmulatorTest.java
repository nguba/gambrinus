package me.nguba.gambrinus.brewpi;

import me.nguba.gambrinus.brewpi.serialization.SparkSerializer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * set the temperature for a sensor address <br>
 * provide the temperature reading for all sensors via serial read <br>
 * split the message into chuncks if necessary, setting the available bytes value correctly<br>
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class SparkSerialEmulatorTest {

  private final SparkSerialEmulator emulator = new SparkSerialEmulator(new SparkSerializer());

  @Test
  @DisplayName("returns available devices in one whole message")
  void availableInOneRead() throws Exception {

    emulator.read(null, 0);
  }
}
