package me.nguba.gambrinus;

import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ActuatorService
{
    private final ThreadPoolExecutor executor;

    private final VesselRepository repository;

    private ActuatorService(final ThreadPoolExecutor executor, final VesselRepository repository)
    {
        this.executor = executor;
        this.repository = repository;
    }

    public void poll(final VesselId vesselId, final TimeUnit interval)
    {
        repository.read(vesselId);
    }
}
