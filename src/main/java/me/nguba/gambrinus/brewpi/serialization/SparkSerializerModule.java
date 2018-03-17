package me.nguba.gambrinus.brewpi.serialization;

import me.nguba.gambrinus.brewpi.domain.Device;
import me.nguba.gambrinus.converter.OneWireAddressSerializer;

import com.fasterxml.jackson.databind.module.SimpleModule;

public final class SparkSerializerModule extends SimpleModule {

  private static final long serialVersionUID = -305985347348223776L;

  public SparkSerializerModule() {
    super("Spark Serializer Module");

    addSerializer(OneWireAddressSerializer.serializer());
    addDeserializer(Device.class, new DeviceDeserializer());
  }

}
