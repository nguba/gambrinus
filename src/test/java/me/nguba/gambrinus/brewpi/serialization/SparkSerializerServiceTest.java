package me.nguba.gambrinus.brewpi.serialization;

import me.nguba.gambrinus.TestUtils;
import me.nguba.gambrinus.brewpi.AvailableDevices;
import me.nguba.gambrinus.brewpi.BrewPiMother;
import me.nguba.gambrinus.brewpi.Device;
import me.nguba.gambrinus.hardware.onewire.OneWireAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class SparkSerializerServiceTest {

  private static final Logger LOG = LoggerFactory.getLogger(SparkSerializerServiceTest.class);

  private final SparkSerializerService serializer = new SparkSerializerService();

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

    final Device actual = convertDevice("json/device.json");

    assertThat(actual).isEqualTo(expected);
  }

  private Device convertDevice(final String path) throws IOException {
    final Device readDevice = serializer.toDevice(readFile(path));
    LOG.info("fromJson: {}", readDevice);
    return readDevice;
  }

  private AvailableDevices convertAvailable(final String path) throws IOException {
    final AvailableDevices read = serializer.toAvailable(readFile(path));
    LOG.info("fromJson: {}", read);
    return read;
  }

  @Test
  @DisplayName("read unplugged device")
  void canReadUnpluggedDevice() throws Exception {

    final Device expected = Device.make(null, 0.0);

    final Device actual = convertDevice("json/unpluggedDevice.json");

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("read available devices")
  void canReadAvailableDevices() throws Exception {

    final AvailableDevices expected = BrewPiMother.availableDevices();

    final AvailableDevices actual = convertAvailable("json/availableDevices.json");

    assertThat(actual).isEqualTo(expected);
  }
}
