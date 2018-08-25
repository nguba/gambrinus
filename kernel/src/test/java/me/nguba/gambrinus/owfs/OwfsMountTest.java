package me.nguba.gambrinus.owfs;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.onewire.OneWireAddress;

class OwfsMountTest
{
  private final OwfsMount mount = OwfsMount.from(OwfsMother.root(), OwfsMother.address());

  @Test
  @DisplayName("Returns initialized instance")
  void returnValidObjectOnSuccess()
  {
    assertThat(mount).isNotNull();
  }

  @Test
  @DisplayName("Is Valid when path exists and is a directory")
  void isValid()
  {
    assertThat(mount.isValid()).isTrue();
  }

  @Test
  @DisplayName("Is Not Valid when path doesn't exist")
  void isInvalid()
  {
    assertThat(OwfsMount.from(OwfsMother.root(), OneWireAddress.of("arse")).isValid())
        .isFalse();
  }
}
