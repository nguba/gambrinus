package me.nguba.gambrinus.brewpi;

import me.nguba.gambrinus.converter.OneWireAddressSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class BrewpiMapper {

  private final ObjectMapper mapper = new ObjectMapper();

  public BrewpiMapper() {
    final SimpleModule module = new SimpleModule("Domain Serializers");
    module.addSerializer(OneWireAddressSerializer.serializer());
    mapper.registerModule(module);
  }

  public String toJson(final Object object) throws IOException {
    return mapper.writeValueAsString(object);
  }
}
