package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.command.CommandEvent;
import me.nguba.gambrinus.cqrs.command.EventPublisher;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.process.Temperature;
import me.nguba.gambrinus.process.setpoint.SetpointChanged;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BrewmasterTest implements EventPublisher
{
    private final VesselRepository vessels = new VesselRepository();

    private Brewmaster brewmaster;

    private final VesselId vesselId = VesselId.of("mash");

    private CommandEvent event;

    @BeforeEach
    void setUp()
    {
        vessels.create(Vessel.of(vesselId));

        final MashCommandProcessorFactory commandFactory = new MashCommandProcessorFactory(this,
                                                                                           vessels);
        final MashQueryProcessorFactory queryFactory = new MashQueryProcessorFactory(vessels);

        brewmaster = new Brewmaster(commandFactory, queryFactory);
    }

    @Test
    void heatResultsInEventPublished() throws Exception
    {
        brewmaster.heat(VesselId.of("mash"), Temperature.celsius(55.5));
        assertThat(event).isInstanceOf(SetpointChanged.class);
    }

    @Override
    public <E extends CommandEvent> void publish(final E event)
    {
        this.event = event;
    }

    @Test
    void readTemperarture() throws Exception
    {
        final Temperature temperature = brewmaster.readTemperature(vesselId);
        assertThat(temperature).isEqualTo(Temperature.celsius(0));
    }

    @Test
    void findVessels() throws Exception
    {
        final Vessel[] expected = { Vessel.of(VesselId.of("a")), Vessel.of(VesselId.of("b")) };
        for (final Vessel v : expected) {
            vessels.create(v);
        }

        assertThat(brewmaster.findVessels()).contains(expected);

    }
}
