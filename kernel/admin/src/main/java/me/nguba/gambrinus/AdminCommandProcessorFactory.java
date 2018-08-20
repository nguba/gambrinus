package me.nguba.gambrinus;

import me.nguba.gambrinus.command.vessel.create.CreateVessel;
import me.nguba.gambrinus.command.vessel.create.CreateVesselFactory;
import me.nguba.gambrinus.cqrs.command.CommandProcessor;
import me.nguba.gambrinus.cqrs.command.EventPublisher;
import me.nguba.gambrinus.equipment.VesselRepository;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class AdminCommandProcessorFactory
{
    private final EventPublisher publisher;
    
    private VesselRepository     repo;

    public AdminCommandProcessorFactory(final EventPublisher publisher, VesselRepository repo)
    {
        this.publisher = publisher;
        this.repo = repo;
    }

    public CommandProcessor make()
    {
        final CommandProcessor processor = new CommandProcessor(publisher);
        processor.register(CreateVessel.class, CreateVesselFactory.on(repo));
        return processor;
    }
}
