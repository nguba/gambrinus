package me.nguba.gambrinus.domain.hardware;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BrewPiEmulatorTest {

  private final BrewPiEmulator emulator = new BrewPiEmulator();
  
  @Test
  @DisplayName("Emit readings when polled")
  void startEmittingReadingsWhenPolled() {
    
  }
  
  @Test
  void addSensor() {
    emulator.add(SensorAddress.MASH_TUN);
    emulator.read();
  }
  
  @Test
  @DisplayName("Increase Mash temperature")
  void increaseTemperatureForMash() {
    emulator.temperature(SensorAddress.MASH_TUN, 20.0);
    emulator.read();
  }

}
