package me.nguba.gambrinus.query.temperature.read;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.equipment.VesselId;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class ReadTemperatureTest
{
  private final VesselId  vesselId = VesselId.of("read temperature");
  private ReadTemperature query;

  @BeforeEach
  void setUp() throws Exception
  {
    query = ReadTemperature.from(vesselId);
  }

  @Test
  void testGetVesselId()
  {
    assertThat(query.getVesselId()).isEqualTo(vesselId);
  }

}
