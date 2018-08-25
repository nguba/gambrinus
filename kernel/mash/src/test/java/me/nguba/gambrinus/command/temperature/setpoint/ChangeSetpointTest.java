package me.nguba.gambrinus.command.temperature.setpoint;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.process.Temperature;

class ChangeSetpointTest
{
  private final ChangeSetpoint command = ChangeSetpoint.on(VesselId.of("setpoint test"),
                                                           Temperature.celsius(58.0));

  @Test
  void hasVesselId()
  {
    assertEquals(command.getId(), VesselId.of("setpoint test"));
  }

  @Test
  void hasSetpoint()
  {
    assertEquals(command.getSetpoint(), Temperature.celsius(58.0));
  }
}
