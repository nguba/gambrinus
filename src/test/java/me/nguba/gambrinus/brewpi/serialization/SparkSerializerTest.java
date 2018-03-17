package me.nguba.gambrinus.brewpi.serialization;

import me.nguba.gambrinus.TestUtils;
import me.nguba.gambrinus.brewpi.domain.BrewPiMother;
import me.nguba.gambrinus.brewpi.domain.Device;
import me.nguba.gambrinus.domain.hardware.OneWireAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class SparkSerializerTest {

  private static final Logger LOG = LoggerFactory.getLogger(SparkSerializerTest.class);

  private final SparkSerializer serializer = new SparkSerializer();

  private final Device device = BrewPiMother.emptyDevice();

  @Test
  void ignoresNullValues() throws Exception {
    assertThat(convert(device)).doesNotContain("null");
  }

  @Test
  void doesNotIncludeIsGetters() throws Exception {
    assertThat(convert(device)).doesNotContain("valid");
  }

  @Test
  void doesNotIndentOutput() throws Exception {
    assertThat(convert(device)).doesNotContain("\n");
  }

  private String convert(final Device device) throws IOException {
    final String json = serializer.toJson(device);
    LOG.info("toJson: {}", json);
    return json;
  }

  @Test
  void canWriteDevice() throws Exception {

    final String json = convert(Device.make(OneWireAddress.valueOf("2851B75D07000026"), 58.0));

    final String expected = readFile("json/device.json");

    assertThat(json).isEqualTo(expected);
  }

  private static String readFile(final String path) throws IOException {
    final String content = TestUtils.readFile(path);
    LOG.info("readFile({}): {}", path, content);
    return content;
  }

  @Test
  void canReadPluggedInDevice() throws Exception {

    final Device expected = Device.make(OneWireAddress.valueOf("2851B75D07000026"), 58.0);

    final Device actual = convert("json/device.json");

    assertThat(actual).isEqualTo(expected);
  }

  private Device convert(final String path) throws IOException {
    final Device readDevice = serializer.readDevice(readFile(path));
    LOG.info("fromJson: {}", readDevice);
    return readDevice;
  }

  @Test
  void canReadUnpluggedDevice() throws Exception {

    final Device expected = Device.make(null, 0.0);

    final Device actual = convert("json/unpluggedDevice.json");

    assertThat(actual).isEqualTo(expected);
  }
}
