package me.nguba.gambrinus;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.command.temperature.setpoint.SetpointChanged;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.event.EventPublisher;
import me.nguba.gambrinus.event.MutatorEvent;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.owfs.OwfsRoot;
import me.nguba.gambrinus.owfs.OwfsSensor;
import me.nguba.gambrinus.process.Temperature;

class BrewmasterTest implements EventPublisher
{
  private final VesselRepository vessels = new VesselRepository();

  private Brewmaster brewmaster;

  private final VesselId vesselId = VesselId.of("mash");

  private Optional<MutatorEvent> currentEvent;
  
  @BeforeEach
  void setUp()
  {
    vessels.create(Vessel.of(vesselId, OwfsSensor.from(OwfsRoot.test(), OneWireAddress.empty())));

    final BrewCommands commandFactory = new BrewCommands(vessels, this);
    final BrewQueries queryFactory = new BrewQueries(vessels);

    brewmaster = new Brewmaster(commandFactory, queryFactory);
  }

  @Test
  void readTemperarture() throws Exception
  {
    final Temperature temperature = brewmaster.readTemperature(vesselId);
    assertThat(temperature).isEqualTo(Temperature.celsius(0));
  }

  @Test
  void heatUpdatesSetpoint() throws Exception
  {
    brewmaster.heat(vesselId, Temperature.celsius(65.5));
    assertThat(vessels.read(vesselId).get().setpoint()).isEqualTo(Temperature.celsius(65.5));
  }
  
  @Test
  void heatBroadcastsSetpoint() throws Exception
  {
    brewmaster.heat(vesselId, Temperature.celsius(65.5));
    
    SetpointChanged event = (SetpointChanged)currentEvent.get();
    
    assertThat(event.getSetpoint()).isEqualTo(Temperature.celsius(65.5));
    assertThat(event.getVesselId()).isEqualTo(vesselId);
    
  }

  @Override
  public <E extends MutatorEvent> void publish(final E event)
  {
    currentEvent = Optional.of(event);
  }

  @Override
  public void subscribe(final Object recipient)
  {
  }

}
