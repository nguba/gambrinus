package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.command.CommandEvent;
import me.nguba.gambrinus.cqrs.command.EventPublisher;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.process.Temperature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BrewmasterTest implements EventPublisher
{
    private final VesselRepository vessels = new VesselRepository();
    
    private Brewmaster brewmaster;

    @BeforeEach
    void setUp()
    {
        vessels.create(Vessel.of(VesselId.of("mash")));
        
        CommandProcessorFactory factory = new CommandProcessorFactory(this, vessels);
        
        brewmaster = new Brewmaster(factory.make());
    }

    @Test
    void heatResultsInEventPublished() throws Exception
    {
        brewmaster.heat(VesselId.of("mash"), Temperature.celsius(55.5));

    }

    @Override
    public <E extends CommandEvent> void publish(final E onCompletion)
    {
        System.out.println(onCompletion);
    }

}
