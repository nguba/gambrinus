package me.nguba.gambrinus.brewpi.domain;

import me.nguba.gambrinus.domain.hardware.OneWireAddress;
import me.nguba.gambrinus.domain.hardware.SensorAddress;
import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class DeviceTest {

  @Test
  void equalsContract() {
    EqualsVerifier.forClass(Device.class).withOnlyTheseFields("a", "v").verify();
  }

  @Test
  void idReturnsOneWireAddress() {

    final Device actual = BrewPiMother.boilKettleDevice(0.0);
    assertThat(actual.id())
        .isEqualTo(OneWireAddress.valueOf(SensorAddress.BOIL_KETTLE.toString()));
  }

  @Test
  @DisplayName("not assigned when no address present")
  void isNotAssignedWhenNoAddressGiven() {
    assertThat(BrewPiMother.emptyDevice().isAssigned()).isFalse();
  }

  @Test
  @DisplayName("assigned when ddress present")
  void isAssignedWhenAddressGiven() {
    assertThat(BrewPiMother.mashTunDevice(0.0).isAssigned()).isTrue();
  }

  @Test
  @DisplayName("is valid when address valid")
  void isValidWhenAddressValid() {
    assertThat(BrewPiMother.mashTunDevice(0.0).isValid()).isTrue();
  }

  @Test
  @DisplayName("is invalid when address invalid")
  void isInvalidWhenAddressValid() {
    assertThat(BrewPiMother.device(SensorAddress.INVALID, 0.0).isValid()).isFalse();
  }

  @Test
  @DisplayName("is invalid when address not assigned")
  void isInvalidWhenAddressNotAssigned() {
    assertThat(BrewPiMother.emptyDevice().isValid()).isFalse();
  }

}
