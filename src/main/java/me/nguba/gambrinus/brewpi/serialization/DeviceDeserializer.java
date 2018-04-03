package me.nguba.gambrinus.brewpi.serialization;

import me.nguba.gambrinus.brewpi.Device;
import me.nguba.gambrinus.domain.hardware.onewire.OneWireAddress;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public final class DeviceDeserializer extends JsonDeserializer<Device> {

  @Override
  public Device deserialize(final JsonParser p, final DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    final ObjectCodec codec = p.getCodec();
    final JsonNode node = codec.readTree(p);

    final JsonNode address = safeGet(node, "a");
    final JsonNode value = safeGet(node, "v");

    if (address != null) {
      return Device.make(OneWireAddress.valueOf(address.textValue()), value.asDouble());
    }
    return Device.make(null, 0);
  }

  private static JsonNode safeGet(final JsonNode node, final String fieldName) {
    if (node.has(fieldName)) {
      return node.get(fieldName);
    }
    return null;
  }

}
