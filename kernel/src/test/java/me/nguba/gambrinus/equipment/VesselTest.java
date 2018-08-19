package me.nguba.gambrinus.equipment;

import me.nguba.gambrinus.process.Temperature;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VesselTest
{
    private final Vessel vessel = Vessel.of(VesselId.of("junit"));

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
