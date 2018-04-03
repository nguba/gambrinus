package me.nguba.gambrinus.brewpi.domain;

import me.nguba.gambrinus.domain.hardware.onewire.AddressMother;
import me.nguba.gambrinus.domain.hardware.onewire.OneWireAddress;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class BrewPiMother {

  private BrewPiMother() {
  }

  public static Device emptyDevice() {
    return Device.make(null, 0.0);
  }

  public static Device device(final AddressMother address, final double value) {
    return Device
        .make(OneWireAddress.valueOf(address.toString()), value);
  }

  public static AvailableDevices availableDevices() {
    AvailableDevices devices = AvailableDevices.make();
    devices.add(BrewPiMother.emptyDevice());
    devices.add(BrewPiMother.boilKettleDevice(0));
    devices.add(BrewPiMother.hotLiquorDevice(0));
    devices.add(BrewPiMother.emptyDevice());
    devices.add(BrewPiMother.mashTunDevice(0));
    devices.add(BrewPiMother.emptyDevice());
    return devices;
  }

  public static Device mashTunDevice(final double value) {
    return device(AddressMother.MASH_TUN, value);
  }

  public static Device boilKettleDevice(final double value) {
    return device(AddressMother.BOIL_KETTLE, value);
  }

  public static Device hotLiquorDevice(final double value) {
    return device(AddressMother.HOT_LIQUOR_TANK, value);
  }

  public static OneWireAddress mashTunAddress() {
    return OneWireAddress.valueOf(AddressMother.MASH_TUN.toString());
  }

  public static OneWireAddress boilKettleAddress() {
    return OneWireAddress.valueOf(AddressMother.BOIL_KETTLE.toString());
  }
}
