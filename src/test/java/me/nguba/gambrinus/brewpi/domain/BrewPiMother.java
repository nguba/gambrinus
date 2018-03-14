package me.nguba.gambrinus.brewpi.domain;

import me.nguba.gambrinus.brewpi.domain.AvailableDevices;
import me.nguba.gambrinus.brewpi.domain.Device;
import me.nguba.gambrinus.domain.hardware.OneWireAddress;
import me.nguba.gambrinus.domain.hardware.SensorAddress;

/**
 * 
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class BrewPiMother {

  private BrewPiMother() {
  }

  public static Device device() {
    return Device
        .from(OneWireAddress.valueOf(SensorAddress.BOIL_KETTLE.toString()), 58.0);
  }

  public static AvailableDevices availableDevices() {
    AvailableDevices devices = AvailableDevices.instance();
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

  public static OneWireAddress mashTunAddress() {
    return OneWireAddress.valueOf(SensorAddress.MASH_TUN.toString());
  }

  public static OneWireAddress boilKettleAddress() {
    return OneWireAddress.valueOf(SensorAddress.BOIL_KETTLE.toString());
  }
}
