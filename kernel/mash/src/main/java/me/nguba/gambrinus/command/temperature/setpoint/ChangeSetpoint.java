package me.nguba.gambrinus.command.temperature.setpoint;

import me.nguba.gambrinus.command.VesselCommand;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.process.Temperature;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class ChangeSetpoint implements VesselCommand
{
  private final VesselId vesselId;

  private final Temperature setpoint;

  private ChangeSetpoint(final VesselId vesselId, final Temperature setpoint)
  {
    this.vesselId = vesselId;
    this.setpoint = setpoint;
  }

  public static ChangeSetpoint on(final VesselId vesselId, final Temperature setpoint)
  {
    return new ChangeSetpoint(vesselId, setpoint);
  }

  @Override
  public VesselId getId()
  {
    return vesselId;
  }

  public Temperature getSetpoint()
  {
    return setpoint;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + (setpoint == null ? 0 : setpoint.hashCode());
    result = prime * result + (vesselId == null ? 0 : vesselId.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final ChangeSetpoint other = (ChangeSetpoint) obj;
    if (setpoint == null) {
      if (other.setpoint != null)
        return false;
    } else if (!setpoint.equals(other.setpoint))
      return false;
    if (vesselId == null) {
      if (other.vesselId != null)
        return false;
    } else if (!vesselId.equals(other.vesselId))
      return false;
    return true;
  }
}
