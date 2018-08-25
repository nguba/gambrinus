package me.nguba.gambrinus;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.onewire.OneWireAddress;

class AdministratorTest
{
  private final VesselRepository vessels = new VesselRepository();

  private Administrator admin;

  @BeforeEach
  void setUp()
  {
    admin = new Administrator(new AdminCommands(vessels),
                              new AdminQueries(vessels));
  }

  @Test
  void findVessels() throws Exception
  {
    final Vessel[] expected = { Vessel.inactive(VesselId.of("a")),
        Vessel.inactive(VesselId.of("b")) };
    for (final Vessel v : expected)
      vessels.create(v);

    assertThat(admin.findVessels()).containsOnly(expected);
  }

  @Test
  void findAddresses() throws Exception
  {
    final OneWireAddress a = OneWireAddress.of("28.273B5D070000");
    final OneWireAddress b = OneWireAddress.of("28.4BBB68080000");

    assertThat(admin.findAddresses("src/test/resources/owfs")).containsOnly(a, b);
  }
}
