package me.nguba.gambrinus.query.onewire;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.onewire.OneWireAddress;

class FindOneWireAddressResultTest
{
  @Test
  void returnsEmptySetByDefault()
  {
    final FindOneWireAddressResult sensorsResult = FindOneWireAddressResult
        .from(new OneWireAddress[0]);
    assertThat(sensorsResult.getResult().get()).isEmpty();
  }

  @Test
  void noDuplicates()
  {
    final OneWireAddress a = OneWireAddress.of("28.273B5D070000");
    final OneWireAddress b = OneWireAddress.of("28.273B5D070000");
    final OneWireAddress c = OneWireAddress.of("28.4BBB68080000");
    final OneWireAddress[] sensors = { a, b, c };

    assertThat(FindOneWireAddressResult.from(sensors).getResult().get()).containsOnly(a, c);
  }

  @Test
  void nullAddresses()
  {
    assertThat(FindOneWireAddressResult.from(null).getResult().get()).isEmpty();
  }
}
