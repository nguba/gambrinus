package me.nguba.gambrinus.eventstore;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import me.nguba.gambrinus.InfrastructureTest;
import me.nguba.gambrinus.command.temperature.setpoint.SetpointChanged;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.event.MutatorEvent;
import me.nguba.gambrinus.process.Temperature;

@InfrastructureTest
class EventStoreTest
{
  @Autowired
  private EventStore eventStore;
  
  @Test
  void sourceVesselSetpoint() throws Exception
  {
    MutatorEvent event = SetpointChanged.on(VesselId.of("mash"), Temperature.celsius(72.0));
    eventStore.record(event);
  }

}
