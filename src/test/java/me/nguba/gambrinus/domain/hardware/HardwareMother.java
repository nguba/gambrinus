package me.nguba.gambrinus.domain.hardware;

import me.nguba.gambrinus.brewpi.domain.Device;
import me.nguba.gambrinus.domain.hardware.onewire.AddressMother;
import me.nguba.gambrinus.domain.hardware.onewire.OneWireAddress;
import me.nguba.gambrinus.domain.hardware.onewire.OneWireSensor;
import me.nguba.gambrinus.domain.hardware.onewire.OneWireTemperatureSensor;
import me.nguba.gambrinus.domain.process.Temperature;

import java.util.UUID;

public enum HardwareMother {
  ;

  public static OneWireSensor boilKettleSensor() {
    return OneWireSensor.make(boilKettleAddress());
  }

  public static OneWireAddress boilKettleAddress() {
    return OneWireAddress
        .valueOf(AddressMother.BOIL_KETTLE.toString());
  }

  public static OneWireAddress hotLiquorTankAddress() {
    return OneWireAddress
        .valueOf(AddressMother.HOT_LIQUOR_TANK.toString());
  }

  public static OneWireSensor mashTunSensor() {
    return OneWireSensor.make(mashTunAddress());
  }

  public static OneWireAddress mashTunAddress() {
    return OneWireAddress
        .valueOf(AddressMother.MASH_TUN.toString());
  }

  public static TemperatureSensor<OneWireAddress, Temperature> boilKettleTemperatureSensor() {
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
