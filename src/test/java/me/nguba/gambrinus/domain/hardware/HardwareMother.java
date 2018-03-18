package me.nguba.gambrinus.domain.hardware;

public enum HardwareMother {
  ;
 
  public static OneWireSensor boilKettleSensor() {
    return OneWireSensor.make(OneWireAddress
        .valueOf(SensorAddress.BOIL_KETTLE.toString()));
  }
  
  public static TemperatureSensor boilKettleTemperatureSensor() {
    return TemperatureSensor.make(boilKettleSensor());
  }
}
