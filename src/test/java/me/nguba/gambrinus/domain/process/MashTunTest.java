package me.nguba.gambrinus.domain.process;

import me.nguba.gambrinus.domain.hardware.Vessel;
import me.nguba.gambrinus.domain.hardware.onewire.OneWireAddress;
import me.nguba.gambrinus.domain.hardware.onewire.OneWireSensor;
import me.nguba.gambrinus.domain.hardware.onewire.OneWireTemperatureSensor;

import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class MashTunTest {

  @Test
  void testId() {
    System.out.println(Vessel
        .make(UUID.randomUUID(),
                "Mash Tun",
                OneWireTemperatureSensor
                    .make(OneWireSensor.make(OneWireAddress.valueOf("2851B75D07000026")))));
  }

}
