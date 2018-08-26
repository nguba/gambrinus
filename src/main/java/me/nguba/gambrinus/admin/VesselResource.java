/**
 * 
 */
package me.nguba.gambrinus.admin;

import org.springframework.hateoas.ResourceSupport;

import me.nguba.gambrinus.equipment.Vessel;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 * 
 */
public final class VesselResource extends ResourceSupport
{
  private final String sensor;
  private final String processValue;
  private final String setpoint;

  public VesselResource(Vessel entity)
  {
    sensor = entity.address().isValid() ? entity.address().toString() : null;
    processValue = entity.processValue().toString();
    setpoint = entity.setpoint().toString();
  }

  public String getSensor()
  {
    return sensor;
  }

  public String getProcessValue()
  {
    return processValue;
  }

  public String getSetpoint()
  {
    return setpoint;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((sensor == null) ? 0 : sensor.hashCode());
    result = prime * result + ((processValue == null) ? 0 : processValue.hashCode());
    result = prime * result + ((setpoint == null) ? 0 : setpoint.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    VesselResource other = (VesselResource) obj;
    if (sensor == null) {
      if (other.sensor != null)
        return false;
    } else if (!sensor.equals(other.sensor))
      return false;
    if (processValue == null) {
      if (other.processValue != null)
        return false;
    } else if (!processValue.equals(other.processValue))
      return false;
    if (setpoint == null) {
      if (other.setpoint != null)
        return false;
    } else if (!setpoint.equals(other.setpoint))
      return false;
    return true;
  }

}
