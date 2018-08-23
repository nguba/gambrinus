package me.nguba.gambrinus;

import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.event.EventPublisher;
import me.nguba.gambrinus.event.MutatorEvent;
import me.nguba.gambrinus.process.Temperature;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BrewmasterTest implements EventPublisher
{
    private final VesselRepository vessels = new VesselRepository();

    private Brewmaster brewmaster;

    private final VesselId vesselId = VesselId.of("mash");

    @BeforeEach
    void setUp()
    {
        vessels.create(Vessel.inactive(vesselId));

        final MashCommands commandFactory = new MashCommands(vessels, this);
        final MashQueries queryFactory = new MashQueries(vessels);

        brewmaster = new Brewmaster(commandFactory, queryFactory);
    }

    @Test
    void readTemperarture() throws Exception
    {
        final Temperature temperature = brewmaster.readTemperature(vesselId);
        assertThat(temperature).isEqualTo(Temperature.celsius(0));
    }
    
    @Test
    void heat() throws Exception 
    {
        brewmaster.heat(vesselId, Temperature.celsius(65.5));
    }

    @Override
    public <E extends MutatorEvent> void publish(E event)
    {
        System.out.println(event);
    }

    @Override
    public void subscribe(Object recipient)
    {
    }

}
