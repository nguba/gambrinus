package me.nguba.gambrinus.domain.hardware;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    assertThat(OneWireAddress.valueOf(SensorAddress.INVALID.toString()).isValid()).isFalse();
  }

  @Test
  void toStringReturnsAddressValue() {
    String expected = SensorAddress.MASH_TUN.toString();
    assertThat(OneWireAddress.valueOf(expected).toString()).isEqualTo(expected);
  }
  
  @Test
  void hashCodeEqualsContract() {
    EqualsVerifier.forClass(OneWireAddress.class);
  }
}
