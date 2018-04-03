package me.nguba.gambrinus.brewpi.serialization;

import me.nguba.gambrinus.brewpi.Device;
import me.nguba.gambrinus.hardware.onewire.OneWireAddressSerializer;

import com.fasterxml.jackson.databind.module.SimpleModule;

public final class SparkSerializerModule extends SimpleModule {

  private static final long serialVersionUID = -305985347348223776L;

  public SparkSerializerModule() {
    super("Spark Serializer Module");

    addSerializer(new OneWireAddressSerializer());
    addDeserializer(Device.class, new DeviceDeserializer());
  }

}
