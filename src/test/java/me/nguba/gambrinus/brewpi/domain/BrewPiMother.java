package me.nguba.gambrinus.brewpi.domain;

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
    return Device.make(null, 0.0);
  }

  public static Device device(final SensorAddress address, final double value) {
    return Device
        .make(OneWireAddress.valueOf(address.toString()), value);
  }

  public static AvailableDevices availableDevices() {
    final AvailableDevices devices = AvailableDevices.instance();
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
