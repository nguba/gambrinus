package me.nguba.gambrinus.brewpi;

import me.nguba.gambrinus.SerialDevice;
import me.nguba.gambrinus.brewpi.domain.AvailableDevices;
import me.nguba.gambrinus.brewpi.domain.Device;
import me.nguba.gambrinus.brewpi.serialization.SparkSerializer;

import java.io.IOException;

public class SparkSerialEmulator implements SerialDevice {

  private final SparkSerializer serializer;

  public SparkSerialEmulator(final SparkSerializer serializer) {
    this.serializer = serializer;
  }

  @Override
  public int available() {

    return 0;
  }

  @Override
  public int read(final byte[] buffer, final int bytesToRead) throws InterruptedException {
    final AvailableDevices availableDevices = AvailableDevices.instance();
    availableDevices.add(Device.make(null, 0.0));
    availableDevices.add(Device.make(null, 0.0));
    try {
      System.out.println(serializer.toJson(availableDevices));
    } catch (final IOException e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public boolean hasAvailable() {
    return false;
  }
}
