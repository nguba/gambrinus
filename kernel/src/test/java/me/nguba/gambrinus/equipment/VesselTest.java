package me.nguba.gambrinus.equipment;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.process.Temperature;

class VesselTest
{
  private final Vessel vessel = Vessel.inactive(VesselId.of("junit"));

  @Test
  @DisplayName("Temperature should be 0 Celsius by default")
  public void TemperatureIsZeroByDefault()
  {
    assertThat(vessel.setpoint()).isEqualTo(Temperature.celsius(0));
  }

  @Test
  @DisplayName("Temperature is mutable")
  public void canChangeTemperature()
  {
    vessel.setpoint(Temperature.celsius(65.5));

    assertThat(vessel.setpoint()).isEqualTo(Temperature.celsius(65.5));
  }
}
