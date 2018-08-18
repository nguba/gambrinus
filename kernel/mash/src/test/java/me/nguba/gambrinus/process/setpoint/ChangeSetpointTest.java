package me.nguba.gambrinus.process.setpoint;

import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.process.Temperature;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChangeSetpointTest
{
    private final ChangeSetpoint command = ChangeSetpoint.on(VesselId.of("setpoint test"),
                                               Temperature.celsius(58.0));

    @Test
    void hasVesselId()
    {
        assertEquals(command.getVesselId(), VesselId.of("setpoint test"));
    }

    @Test
    void hasSetpoint()
    {
        assertEquals(command.getSetpoint(), Temperature.celsius(58.0));
    }
}
