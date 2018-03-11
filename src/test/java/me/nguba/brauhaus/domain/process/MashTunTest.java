package me.nguba.brauhaus.domain.process;

import me.nguba.brauhaus.domain.hardware.OneWireAddress;
import me.nguba.brauhaus.domain.hardware.OneWireSensor;
import me.nguba.brauhaus.domain.hardware.TemperatureSensor;
import me.nguba.brauhaus.domain.hardware.Vessel;

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
        .vessel(UUID.randomUUID(),
                   "Mash Tun",
                   TemperatureSensor
                       .sensor(OneWireSensor.sensor(OneWireAddress.valueOf("2851B75D07000026")))));
  }

}
