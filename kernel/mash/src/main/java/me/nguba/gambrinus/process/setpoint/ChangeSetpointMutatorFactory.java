package me.nguba.gambrinus.process.setpoint;

import me.nguba.gambrinus.cqrs.command.Command;
import me.nguba.gambrinus.cqrs.command.Mutator;
import me.nguba.gambrinus.cqrs.command.MutatorFactory;
import me.nguba.gambrinus.equipment.VesselRepository;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ChangeSetpointMutatorFactory implements MutatorFactory
{

    private VesselRepository vessels;

    private ChangeSetpointMutatorFactory(VesselRepository vessels)
    {
        this.vessels = vessels;
    }
    
    public static ChangeSetpointMutatorFactory from(VesselRepository vessels) {
        return new ChangeSetpointMutatorFactory(vessels);
    }

    @Override
    public Mutator<? extends Command> make()
    {
        return ChangeSetpointMutator.from(vessels);
    }

}
