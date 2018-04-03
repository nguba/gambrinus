package me.nguba.gambrinus.hardware.onewire;

import me.nguba.gambrinus.hardware.HardwareMother;
import me.nguba.gambrinus.hardware.onewire.OneWireAddress;
import me.nguba.gambrinus.hardware.onewire.OneWireAddressSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OneWireAddressSerializerTest {

  private final OneWireAddressSerializer serailizer = new OneWireAddressSerializer();

  private final ObjectMapper mapper = new ObjectMapper();

  @BeforeEach
  void setUp() {
    final SimpleModule module = new SimpleModule();
    module.addSerializer(serailizer);
    mapper.registerModule(module);
  }

  @Test
  void serialiseOneWireAddress() throws Exception {
    final OneWireAddress address = HardwareMother.boilKettleAddress();

    final String value = mapper.writeValueAsString(address);

    assertThat(value).isEqualTo("\"2851B75D07000026\"");
  }

}
