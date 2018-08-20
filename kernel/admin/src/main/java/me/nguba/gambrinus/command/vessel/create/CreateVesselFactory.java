package me.nguba.gambrinus.command.vessel.create;

import me.nguba.gambrinus.cqrs.command.Command;
import me.nguba.gambrinus.cqrs.command.Mutator;
import me.nguba.gambrinus.cqrs.command.MutatorFactory;
import me.nguba.gambrinus.equipment.VesselRepository;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class CreateVesselFactory implements MutatorFactory
{
    private final VesselRepository repo;

    private CreateVesselFactory(final VesselRepository repo)
    {
        this.repo = repo;
    }

    public static CreateVesselFactory on(final VesselRepository repo)
    {
        return new CreateVesselFactory(repo);
    }

    @Override
    public Mutator<? extends Command> make()
    {
        return new CreateVesselMutator(repo);
    }

}
