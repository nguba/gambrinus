package me.nguba.gambrinus;

import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;

import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ActuatorService
{
    private final ThreadPoolExecutor executor;

    private final VesselRepository repository;
    
    private ActuatorService(ThreadPoolExecutor executor, VesselRepository repository)
    {
        this.executor = executor;
        this.repository = repository;
    }

    public void poll(VesselId vesselId, TimeUnit interval)
    {
        Optional<Vessel> vessel = repository.read(vesselId);
    }
}
