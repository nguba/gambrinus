package me.nguba.gambrinus.equipment;

import me.nguba.gambrinus.ddd.Aggregate;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.owfs.OwfsSensor;
import me.nguba.gambrinus.process.Temperature;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Vessel extends Aggregate<VesselId>
{
  private Temperature setpoint = Temperature.celsius(0);

  private Temperature processValue = Temperature.celsius(0);

  private OwfsSensor sensor;

  private Vessel(final VesselId id, final OwfsSensor sensor)
  {
    super(id);
    this.sensor = sensor;
  }

  /**
   * Returns a vessel without assigned sensor.
   * 
   * @param id
   * @return the inactive vessel
   */
  public static Vessel inactive(final VesselId id)
  {
    return new Vessel(id, null);
  }

  public static Vessel of(final VesselId id, final OwfsSensor sensor)
  {
    return new Vessel(id, sensor);
  }

  public Temperature setpoint()
  {
    return setpoint;
  }

  public void setpoint(final Temperature setpoint)
  {
    this.setpoint = setpoint;
  }

  public Temperature processValue()
  {
    return processValue;
  }

  public void processValue(final Temperature processValue)
  {
    this.processValue = processValue;
  }

  public void assign(final OwfsSensor sensor)
  {
    this.sensor = sensor;
  }

  public OneWireAddress address()
  {
    if (sensor == null)
      return OneWireAddress.empty();

    return sensor.getId();
  }
}
