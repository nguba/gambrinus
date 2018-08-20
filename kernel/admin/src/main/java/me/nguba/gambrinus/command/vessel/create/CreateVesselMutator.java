package me.nguba.gambrinus.command.vessel.create;

import me.nguba.gambrinus.cqrs.command.MutatorEvent;
import me.nguba.gambrinus.cqrs.command.Mutator;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.owfs.OwfsSensor;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class CreateVesselMutator implements Mutator<CreateVessel>
{
    private final VesselRepository repo;

    public CreateVesselMutator(VesselRepository repo)
    {
        this.repo = repo;
    }

    @Override
    public void mutate(CreateVessel command)
    {
        Vessel vessel = Vessel.of(command.getVesselId());
        repo.create(vessel);
    }

    @Override
    public MutatorEvent onCompletion(CreateVessel command)
    {
        return VesselCreated.from(command.getVesselId());
    }

    @Override
    public void validate(CreateVessel command, Errors errors)
    {
        if(command.getVesselId() == null)
            errors.add(Reason.from("VesselId cannot be null."));
        
        if(command.getAddress() == null)
            errors.add(Reason.from("OneWireAddress cannot be null."));
        
        if(repo.read(command.getVesselId()).isPresent()) 
            errors.add(Reason.from("Vessel already configured."));
    }

}
