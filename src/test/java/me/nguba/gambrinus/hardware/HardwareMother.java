package me.nguba.gambrinus.hardware;

import me.nguba.gambrinus.brewpi.Device;
import me.nguba.gambrinus.hardware.onewire.OneWireAddress;
import me.nguba.gambrinus.hardware.onewire.OneWireAddressRegistry;
import me.nguba.gambrinus.hardware.onewire.OneWireSensor;
import me.nguba.gambrinus.hardware.onewire.OneWireTemperatureSensor;

import java.util.UUID;

public enum HardwareMother {
  ;

  public static OneWireSensor boilKettleSensor() {
    return OneWireSensor.make(boilKettleAddress());
  }

  public static OneWireAddress boilKettleAddress() {
    return OneWireAddress
        .valueOf(OneWireAddressRegistry.BOIL_KETTLE.toString());
  }

  public static OneWireAddress hotLiquorTankAddress() {
    return OneWireAddress
        .valueOf(OneWireAddressRegistry.HOT_LIQUOR_TANK.toString());
  }

  public static OneWireSensor mashTunSensor() {
    return OneWireSensor.make(mashTunAddress());
  }

  public static OneWireAddress mashTunAddress() {
    return OneWireAddress
        .valueOf(OneWireAddressRegistry.MASH_TUN.toString());
  }

  public static TemperatureSensor<OneWireAddress> boilKettleTemperatureSensor() {
    return OneWireTemperatureSensor.make(boilKettleSensor());
  }

  public static Vessel mashTun() {
    return Vessel
        .make(UUID.randomUUID(), "Mash Tun", OneWireTemperatureSensor.make(mashTunSensor()));
  }

  public static Device mashTunDevice() {
    return Device.make(mashTunAddress());
  }

  public static Device boilKettleDevice() {
    return Device.make(boilKettleAddress());
  }

  public static Device hotLiquorTankDevice() {
    return Device.make(hotLiquorTankAddress());
  }
}
