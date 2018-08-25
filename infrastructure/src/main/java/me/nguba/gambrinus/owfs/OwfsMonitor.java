package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.command.temperature.process.SetProcessValue;
import me.nguba.gambrinus.command.temperature.process.SetProcessValueHandler;
import me.nguba.gambrinus.cqrs.command.CommandProcessor;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.event.EventPublisher;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.process.Temperature;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class OwfsMonitor {

	private final Map<OneWireAddress, VesselId> assignments = new ConcurrentHashMap<>();

	private VesselRepository repository;

	public OwfsMonitor(final VesselRepository repository) {
		this.repository = repository;

	}

	public void read(final OwfsRoot root) {
		for (final OwfsSensor sensor : root.listSensors()) {

			if (isAssigned(sensor)) {
				try {
					final Temperature read = sensor.read();
					CommandProcessor.from(SetProcessValue.on(getRegisteredVessel(sensor), read), SetProcessValueHandler.from(repository))
							.mutate();
				} catch (final IOException e) {
					e.printStackTrace();
				} catch (ValidationFailed e) {
					e.printStackTrace();
				}
			}
		}
	}

	private boolean isAssigned(final OwfsSensor sensor) {
		return getRegisteredVessel(sensor) != null;
	}

	private VesselId getRegisteredVessel(final OwfsSensor sensor) {
		return assignments.get(sensor.getId());
	}

	public void assign(OneWireAddress address, VesselId vesselId) {
		assignments.put(address, vesselId);
	}

	public VesselId vesselFor(OneWireAddress address) {
		return assignments.get(address);
	}
}
