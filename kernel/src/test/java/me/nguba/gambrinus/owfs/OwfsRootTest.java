package me.nguba.gambrinus.owfs;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.SingleValueObjectFixture;
import me.nguba.gambrinus.onewire.OneWireAddress;

class OwfsRootTest extends SingleValueObjectFixture<File, OwfsRoot>
{
  @Test
  @DisplayName("is invalid if path doesn't exist")
  void pathNotExists()
  {
    assertThat(OwfsRoot.of("non-existent-path").isValid()).isFalse();
  }

  @Test
  @DisplayName("is invalid if path is empty")
  void pathNull()
  {
    assertThat(OwfsRoot.of("").isValid()).isFalse();
  }

  @Override
  protected OwfsRoot makeValueObject()
  {
    return OwfsRoot.of("src/test/resources/owfs");
  }

  @Override
  protected void isValidWhenNotNull()
  {
    // disabled for this suite
  }

  @Test
  @DisplayName("is invalid if path doesn't exist")
  void isValidWhenPathExists()
  {
    assertThat(OwfsRoot.of("src/test/resources/owfs").isValid()).isTrue();
  }

  @Test
  void listAllSensors() throws Exception
  {
    final OwfsSensor one = OwfsSensor.from(getValueObject(),
                                           OneWireAddress.of("28.273B5D070000"));
    final OwfsSensor two = OwfsSensor.from(getValueObject(),
                                           OneWireAddress.of("28.4BBB68080000"));

    final OwfsSensor[] expected = { one, two };

    assertThat(getValueObject().listSensors()).contains(expected);
  }

  @Test
  void listAllSensorsOnInvalidDirectory() throws Exception
  {
    assertThat(OwfsRoot.of("invalid").listSensors()).isEmpty();
  }
}
