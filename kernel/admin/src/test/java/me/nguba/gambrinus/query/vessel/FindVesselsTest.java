package me.nguba.gambrinus.query.vessel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class FindVesselsTest
{
  @Test
  void equalityContract()
  {
    EqualsVerifier.forClass(FindVessels.class).verify();
  }

  @Test
  void createsValidObject()
  {
    assertThat(FindVessels.create()).isEqualTo(FindVessels.INSTANCE);
  }
}
