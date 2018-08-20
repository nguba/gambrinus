package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.command.CommandProcessor;
import me.nguba.gambrinus.cqrs.command.EventPublisher;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.process.setpoint.ChangeSetpoint;
import me.nguba.gambrinus.process.setpoint.ChangeSetpointMutatorFactory;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class MashCommandProcessorFactory
{
    private final VesselRepository vessels;
    private final EventPublisher   publisher;

    public MashCommandProcessorFactory(final EventPublisher publisher,
                                       final VesselRepository vessels)
    {
        this.publisher = publisher;
        this.vessels = vessels;
    }

    public CommandProcessor make()
    {
        final CommandProcessor processor = new CommandProcessor(publisher);
        processor.register(ChangeSetpoint.class, ChangeSetpointMutatorFactory.from(vessels));
        return processor;
    }
}
