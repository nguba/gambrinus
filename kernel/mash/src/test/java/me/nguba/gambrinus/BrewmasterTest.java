package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.command.CommandEvent;
import me.nguba.gambrinus.cqrs.command.EventPublisher;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
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
        vessels.create(Vessel.of(vesselId));

        final CommandProcessorFactory commandFactory = new CommandProcessorFactory(this, vessels);
        final QueryProcessorFactory queryFactory = new QueryProcessorFactory(vessels);

        brewmaster = new Brewmaster(commandFactory.make(), queryFactory.make());
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

    @Test
    void readTemperarture() throws Exception
    {
        final Temperature temperature = brewmaster.readTemperature(vesselId);
        assertThat(temperature).isEqualTo(Temperature.celsius(0));
    }
}