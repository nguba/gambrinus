package me.nguba.gambrinus.command.temperature.process;

import me.nguba.gambrinus.command.VesselHandler;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselRepository;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 * 
 */
public class SetProcessValueHandler extends VesselHandler<SetProcessValue> {

	private SetProcessValueHandler(VesselRepository repo) {
		super(repo);
	}

	public static SetProcessValueHandler from(VesselRepository repo) {
		return new SetProcessValueHandler(repo);
	}

	@Override
	protected void onValidate(SetProcessValue command, Errors errors) {
		if(command.getProcessValue() == null) {
			errors.add(Reason.from("No processValue"));
		}
	}

	@Override
	protected void onVessel(Vessel vessel, SetProcessValue command) {
		vessel.processValue(command.getProcessValue());
	}
}
