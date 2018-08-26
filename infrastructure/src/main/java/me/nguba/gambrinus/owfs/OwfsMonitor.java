package me.nguba.gambrinus.owfs;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import me.nguba.gambrinus.command.temperature.process.SetProcessValue;
import me.nguba.gambrinus.command.temperature.process.SetProcessValueHandler;
import me.nguba.gambrinus.cqrs.command.CommandProcessor;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.process.Temperature;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class OwfsMonitor
{
  private final Map<OneWireAddress, VesselId> assignments = new ConcurrentHashMap<>();

  private final VesselRepository repository;

  public OwfsMonitor(final VesselRepository repository)
  {
    this.repository = repository;
  }

  public void read(final OwfsRoot root)
  {
    for (final OwfsSensor sensor : root.listSensors())
      if (isAssigned(sensor))
        try {
          final Optional<Temperature> read = sensor.read();
          if (read.isPresent())
            CommandProcessor
                .from(SetProcessValue.on(getRegisteredVessel(sensor), read.get()),
                      SetProcessValueHandler.from(repository))
                .mutate();
        } catch (final IOException e) {
          e.printStackTrace();
        } catch (final ValidationFailed e) {
          e.printStackTrace();
        }
  }

  private boolean isAssigned(final OwfsSensor sensor)
  {
    return getRegisteredVessel(sensor) != null;
  }

  private VesselId getRegisteredVessel(final OwfsSensor sensor)
  {
    return assignments.get(sensor.getId());
  }

  public void assign(final OneWireAddress address, final VesselId vesselId)
  {
    assignments.put(address, vesselId);
  }

  public VesselId vesselFor(final OneWireAddress address)
  {
    return assignments.get(address);
  }
}
