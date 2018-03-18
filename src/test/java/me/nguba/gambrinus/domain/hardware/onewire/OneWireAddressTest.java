package me.nguba.gambrinus.domain.hardware.onewire;

import me.nguba.gambrinus.domain.hardware.onewire.OneWireAddress;
import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class OneWireAddressTest {

  @Test
  void isNotValidWhenNullValue() {
    assertThat(OneWireAddress.valueOf(null).isValid()).isFalse();
  }

  @Test
  void isNotValidWhenEmptyValue() {
    assertThat(OneWireAddress.valueOf("").isValid()).isFalse();
  }

  @Test
  void isNotValidDoesntBeginWithOneWirePrefix() {
    assertThat(OneWireAddress.valueOf(AddressMother.INVALID.toString()).isValid()).isFalse();
  }

  @Test
  void toStringReturnsAddressValue() {
    final String expected = AddressMother.MASH_TUN.toString();
    assertThat(OneWireAddress.valueOf(expected).toString()).isEqualTo(expected);
  }

  @Test
  void hashCodeEqualsContract() {
    EqualsVerifier.forClass(OneWireAddress.class).verify();
  }
}
