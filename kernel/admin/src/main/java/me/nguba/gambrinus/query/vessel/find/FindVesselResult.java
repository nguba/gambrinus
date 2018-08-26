/**
 * 
 */
package me.nguba.gambrinus.query.vessel.find;

import java.util.Optional;

import me.nguba.gambrinus.cqrs.query.Result;
import me.nguba.gambrinus.equipment.Vessel;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 *
 */
public final class FindVesselResult implements Result<Vessel>
{
  private Optional<Vessel> vessel;

  public FindVesselResult(Optional<Vessel> vessel)
  {
    this.vessel = vessel;
  }

  @Override
  public Optional<Vessel> getResult()
  {
    return vessel;
  }

  public static FindVesselResult empty()
  {
    return new FindVesselResult(Optional.empty());
  }

  public static FindVesselResult from(Vessel vessel) {
    return new FindVesselResult(Optional.of(vessel));
  }

  public static FindVesselResult of(Optional<Vessel> read)
  {
    return new FindVesselResult(read);
  }
}
