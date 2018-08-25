/**
 * 
 */
package me.nguba.gambrinus.command.temperature.process;

import me.nguba.gambrinus.command.VesselCommand;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.process.Temperature;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 * 
 */
public final class SetProcessValue implements VesselCommand {

	private final VesselId vesselId;

	private final Temperature processValue;

	private SetProcessValue(VesselId vesselId, Temperature processValue) {
		this.vesselId = vesselId;
		this.processValue = processValue;
	}

	public static SetProcessValue on(VesselId vesselId, Temperature processValue) {
		return new SetProcessValue(vesselId, processValue);
	}

	@Override
	public VesselId getId() {
		return vesselId;
	}

	public Temperature getProcessValue() {
		return processValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((processValue == null) ? 0 : processValue.hashCode());
		result = prime * result + ((vesselId == null) ? 0 : vesselId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SetProcessValue other = (SetProcessValue) obj;
		if (processValue == null) {
			if (other.processValue != null)
				return false;
		} else if (!processValue.equals(other.processValue))
			return false;
		if (vesselId == null) {
			if (other.vesselId != null)
				return false;
		} else if (!vesselId.equals(other.vesselId))
			return false;
		return true;
	}
}
