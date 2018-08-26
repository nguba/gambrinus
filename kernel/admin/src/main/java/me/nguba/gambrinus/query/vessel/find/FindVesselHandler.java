/**
 * 
 */
package me.nguba.gambrinus.query.vessel.find;

import java.util.Optional;

import me.nguba.gambrinus.cqrs.query.QueryHandler;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselRepository;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 * 
 */
public final class FindVesselHandler implements QueryHandler<FindVessel, FindVesselResult>
{
  private VesselRepository vessels;

  public FindVesselHandler(VesselRepository vessels)
  {
    this.vessels = vessels;
  }

  @Override
  public void validate(FindVessel query, Errors errors)
  {
    if (query.getId() == null) {
      errors.add(Reason.from("vesselId cannot be null"));
    }
  }

  @Override
  public FindVesselResult query(FindVessel query)
  {
    Optional<Vessel> read = vessels.read(query.getId());
    return FindVesselResult.of(read);
  }

  public static final FindVesselHandler on(VesselRepository vessels)
  {
    return new FindVesselHandler(vessels);
  }
}
