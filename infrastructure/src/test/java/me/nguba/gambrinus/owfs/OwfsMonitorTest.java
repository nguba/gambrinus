package me.nguba.gambrinus.owfs;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.process.Temperature;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class OwfsMonitorTest
{
  private OwfsMonitor monitor;

  private final OwfsRoot root = OwfsRoot.of("src/test/resources/owfs");

  private final VesselRepository vessels = new VesselRepository();

  private final VesselId vesselId = VesselId.of("mash");

  @BeforeEach
  void setUp()
  {
    monitor = new OwfsMonitor(vessels);
  }

  @Test
  void assignSensorToVessel()
  {
    monitor.assign(OneWireAddress.of("28.273B5D070000"), vesselId);

    assertThat(monitor.vesselFor(OneWireAddress.of("28.273B5D070000"))).isEqualTo(vesselId);
  }

  @Test
  void updatesTemperatureOnTargetVessel()
  {
    vessels.create(Vessel.inactive(vesselId));

    assertThat(readProcessValue()).isEqualTo(Temperature.celsius(0));

    monitor.assign(OneWireAddress.of("28.273B5D070000"), vesselId);
    monitor.read(root);

    assertThat(readProcessValue()).isEqualTo(Temperature.celsius(25.7));
  }

  private Temperature readProcessValue()
  {
    return vessels.read(vesselId).get().processValue();
  }

}
