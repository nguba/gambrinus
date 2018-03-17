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

    Device actual = BrewPiMother.boilKettleDevice(0.0);
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
  void isValidWhenAddressGiven() {
    assertThat(BrewPiMother.mashTunDevice(0.0).isAssigned()).isTrue();
  }
}
