package me.nguba.gambrinus.command;

import java.util.Optional;

import me.nguba.gambrinus.cqrs.command.CommandHandler;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselRepository;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public abstract class VesselHandler<C extends VesselCommand> implements CommandHandler<C> {

	protected final VesselRepository repo;

	protected VesselHandler(VesselRepository repo) {
		this.repo = repo;
	}

	@Override
	public final void validate(C command, Errors errors) {
		commandNotNull(command);

		onValidate(command, errors);

		if (command.getId() != null) {
			final Optional<Vessel> vessel = findVessel(command);
			if (!vessel.isPresent()) {
				errors.add(Reason.from(vesselNotFound(command)));
			}
		} else {
			errors.add(Reason.from("No vesselId"));
		}

	}

	private String vesselNotFound(C command) {
		return String.format("Vessel not found: %s", command.getId());
	}

	@Override
	public final void changeStateFor(C command) {
		commandNotNull(command);

		Optional<Vessel> vessel = findVessel(command);
		if (vessel.isPresent()) {
			onVessel(vessel.get(), command);
		} else {
			throw new IllegalArgumentException(vesselNotFound(command));
		}

	}

	protected abstract void onValidate(C command, Errors errors);

	protected abstract void onVessel(Vessel vessel, C command);

	protected void commandNotNull(C command) {
		if (command == null) {
			throw new IllegalArgumentException("Command cannot be null");
		}
	}

	protected Optional<Vessel> findVessel(final C command) {
		return repo.read(command.getId());
	}

}