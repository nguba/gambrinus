package me.nguba.gambrinus.brewpi;

import me.nguba.gambrinus.SerialDevice;
import me.nguba.gambrinus.brewpi.domain.AvailableDevices;
import me.nguba.gambrinus.brewpi.domain.Device;
import me.nguba.gambrinus.domain.hardware.OneWireAddress;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BrewpiSparkEmulator implements SerialDevice {

  private final BrewpiSerializer serializer;

  private ConcurrentMap<OneWireAddress, Device> devices = new ConcurrentHashMap<>();

  public BrewpiSparkEmulator(BrewpiSerializer serializer) {
    this.serializer = serializer;
  }

  @Override
  public int available() {

    return 0;
  }

  @Override
  public int read(byte[] buffer, int bytesToRead) throws InterruptedException {
    AvailableDevices availableDevices = AvailableDevices.instance();
    for (Device device : devices.values()) { 
      availableDevices.add(device);
    }
    
    try {
      System.out.println(serializer.toJson(availableDevices));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public boolean hasAvailable() {
    return false;
  }

  public void add(Device device) {
    devices.put(device.id(), device);
  }

  public List<Device> registered() {
    return new ArrayList<>(devices.values());
  }
}
