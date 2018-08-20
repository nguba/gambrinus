package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.command.MutatorEvent;
import me.nguba.gambrinus.cqrs.command.EventPublisher;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.onewire.OneWireAddress;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdministratorTest implements EventPublisher
{
    private final VesselRepository vessels = new VesselRepository();

    private Administrator admin;

    //private CommandEvent event;

    @BeforeEach
    void setUp()
    {
        admin = new Administrator(new AdminCommandProcessorFactory(this),
                                  new AdminQueryProcessorFactory(vessels));
    }

    @Test
    void findVessels() throws Exception
    {
        final Vessel[] expected = { Vessel.of(VesselId.of("a")), Vessel.of(VesselId.of("b")) };
        for (final Vessel v : expected) {
            vessels.create(v);
        }

        assertThat(admin.findVessels()).containsOnly(expected);
    }

    @Test
    void findAddresses() throws Exception
    {
        final OneWireAddress a = OneWireAddress.of("28.273B5D070000");
        final OneWireAddress b = OneWireAddress.of("28.4BBB68080000");

        assertThat(admin.findAddresses("src/test/resources/owfs")).containsOnly(a, b);
    }

    @Override
    public <E extends MutatorEvent> void publish(final E event)
    {
       // this.event = event;
    }

    @Override
    public void subscribe(final Object recipient)
    {
    }
}
