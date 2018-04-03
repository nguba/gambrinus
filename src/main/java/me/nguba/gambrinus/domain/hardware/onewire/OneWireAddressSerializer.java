package me.nguba.gambrinus.domain.hardware.onewire;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public final class OneWireAddressSerializer extends StdSerializer<OneWireAddress> {

  private static final long serialVersionUID = -4645656429676121404L;

  public OneWireAddressSerializer() {
    super(OneWireAddress.class);
  }

  @Override
  public void serialize(final OneWireAddress value,
                        final JsonGenerator gen,
                        final SerializerProvider provider)
      throws IOException {
    gen.writeString(value.toString());
  }

}
