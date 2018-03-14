package me.nguba.gambrinus.brewpi;

import me.nguba.gambrinus.brewpi.domain.BrewPiMother;
import me.nguba.gambrinus.brewpi.domain.Device;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * set the temperature for a sensor address <br>
 * provide the temperature reading for all sensors via serial read <br>
 * split the message into chuncks if necessary, setting the available bytes value correctly<br>
 * 
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class BrewpiSparkEmulatorTest {

  private final BrewpiSparkEmulator emulator = new BrewpiSparkEmulator(new BrewpiSerializer());

  @Test
  @DisplayName("returns available devices in one whole message")
  void availableInOneRead() throws Exception {
    
    Device mashTun = Device.from(BrewPiMother.mashTunAddress(), 16.0);
    emulator.add(mashTun);
    
    Device boilKettle = Device.from(BrewPiMother.boilKettleAddress(), 18.0);
    emulator.add(boilKettle);

    emulator.read(null, 0);
  }

  @Test
  void addDevice() {
    Device device = Device.from(BrewPiMother.mashTunAddress(), 16.0);
    emulator.add(device);

    assertThat(emulator.registered()).asList().containsExactly(device);
  }
}
