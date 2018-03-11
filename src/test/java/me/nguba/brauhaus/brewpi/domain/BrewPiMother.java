package me.nguba.brauhaus.brewpi.domain;

import me.nguba.brauhaus.domain.hardware.OneWireAddress;
import me.nguba.brauhaus.domain.hardware.SensorAddress;

/**
 * 
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class BrewPiMother {

  private BrewPiMother() {
  }

  public static Device device() {
    return Device
        .device(OneWireAddress.valueOf(SensorAddress.BOIL_KETTLE.toString()), 58.0);
  }
  
  public static AvailableDevices availableDevices() {
    AvailableDevices devices = AvailableDevices.availableDevices();
    devices.add(device());
    devices.add(device());
    devices.add(device());
    devices.add(device());
    devices.add(device());
    devices.add(device());
    devices.add(device());
    devices.add(device());
    devices.add(device());
    devices.add(device());
    return devices;
  }
}
