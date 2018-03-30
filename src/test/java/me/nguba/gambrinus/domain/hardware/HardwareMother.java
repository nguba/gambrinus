package me.nguba.gambrinus.domain.hardware;

import me.nguba.gambrinus.domain.hardware.onewire.AddressMother;
import me.nguba.gambrinus.domain.hardware.onewire.OneWireAddress;
import me.nguba.gambrinus.domain.hardware.onewire.OneWireSensor;
import me.nguba.gambrinus.domain.hardware.onewire.OneWireTemperatureSensor;
import me.nguba.gambrinus.domain.process.Temperature;

import java.util.UUID;

public enum HardwareMother {
  ;

  public static OneWireSensor boilKettleSensor() {
    return OneWireSensor.make(OneWireAddress
        .valueOf(AddressMother.BOIL_KETTLE.toString()));
  }

  public static OneWireSensor mashTunSensor() {
    return OneWireSensor.make(OneWireAddress
        .valueOf(AddressMother.MASH_TUN.toString()));
  }

  public static TemperatureSensor<OneWireAddress, Temperature> boilKettleTemperatureSensor() {
    return OneWireTemperatureSensor.make(boilKettleSensor());
  }

  public static Vessel mashTun() {
    return Vessel
        .make(UUID.randomUUID(), "Mash Tun", OneWireTemperatureSensor.make(mashTunSensor()));
  }
}
