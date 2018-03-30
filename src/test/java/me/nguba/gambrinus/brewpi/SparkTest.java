package me.nguba.gambrinus.brewpi;

import me.nguba.gambrinus.brewpi.domain.AvailableDevices;
import me.nguba.gambrinus.brewpi.domain.BrewPiMother;
import me.nguba.gambrinus.io.SerialStub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SparkTest {

  private Spark spark;

  private final SerialStub serial = new SerialStub();

  @BeforeEach
  void setUp() {
    spark = new Spark(Integer.valueOf(1), serial);
  }

  @Test
  void testPollAvailableDevices() throws Exception {
    AvailableDevices devices = AvailableDevices.make();
    devices.add(BrewPiMother.emptyDevice());
    devices.add(BrewPiMother.boilKettleDevice(0));
    devices.add(BrewPiMother.hotLiquorDevice(0));
    devices.add(BrewPiMother.emptyDevice());
    devices.add(BrewPiMother.mashTunDevice(0));
    devices.add(BrewPiMother.emptyDevice());
    
    int written = serial.write(devices);
    System.out.println(written);
    
    serial.read(null, written);
  }

  @Test
  void testListenForMessages() {
    // fail("Not yet implemented");
  }

}
