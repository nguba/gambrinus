package me.nguba.gambrinus.brewpi.serialization;

import me.nguba.gambrinus.brewpi.domain.AvailableDevices;
import me.nguba.gambrinus.brewpi.domain.Device;
import me.nguba.gambrinus.domain.Service;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class SparkSerializerService implements Service {

  private final ObjectMapper mapper = new ObjectMapper();

  public SparkSerializerService() {

    mapper.registerModule(new SparkSerializerModule());

    mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
    mapper.configure(MapperFeature.AUTO_DETECT_IS_GETTERS, false);
    mapper.setSerializationInclusion(Include.NON_NULL);

  }

  public String toJson(final Object object) throws IOException {
    return mapper.writeValueAsString(object);
  }

  public Device toDevice(final String json) throws IOException {
    return mapper.readValue(json, Device.class);
  }
  
  public AvailableDevices toAvailable(final String json) throws IOException {
    return mapper.readValue(json, AvailableDevices.class);
  }
}
