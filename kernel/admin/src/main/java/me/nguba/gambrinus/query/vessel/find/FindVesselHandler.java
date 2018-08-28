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
  private final VesselRepository vessels;

  public FindVesselHandler(final VesselRepository vessels)
  {
    this.vessels = vessels;
  }

  @Override
  public void validate(final FindVessel query, final Errors errors)
  {
    if (query.getId() == null)
      errors.add(Reason.from("vesselId cannot be null"));
  }

  @Override
  public FindVesselResult query(final FindVessel query)
  {
    final Optional<Vessel> read = vessels.read(query.getId());
    return FindVesselResult.of(read);
  }

  public static FindVesselHandler on(final VesselRepository vessels)
  {
    return new FindVesselHandler(vessels);
  }
}
