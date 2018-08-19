package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.command.CommandEvent;
import me.nguba.gambrinus.cqrs.command.EventPublisher;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdministratorTest implements EventPublisher
{
    private final VesselRepository vessels = new VesselRepository();

    private Administrator admin;
    
    private CommandEvent event;

    @BeforeEach
    void setUp()
    {
        admin = new Administrator(new AdminCommandProcessorFactory(this), new AdminQueryProcessorFactory(vessels));
    }
    
    @Test
    void findVessels() throws Exception
    {
        final Vessel[] expected = { Vessel.of(VesselId.of("a")), Vessel.of(VesselId.of("b")) };
        for (final Vessel v : expected) {
            vessels.create(v);
        }

        assertThat(admin.findVessels()).contains(expected);

    }

    @Override
    public <E extends CommandEvent> void publish(E event)
    {
        this.event = event;
    }
}
