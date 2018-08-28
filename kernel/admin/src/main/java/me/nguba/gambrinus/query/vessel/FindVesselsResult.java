package me.nguba.gambrinus.query.vessel;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import me.nguba.gambrinus.cqrs.query.Result;
import me.nguba.gambrinus.equipment.Vessel;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class FindVesselsResult implements Result<Set<Vessel>>
{
  private final Set<Vessel> vessels = new HashSet<>();

  @Override
  public Optional<Set<Vessel>> getResult()
  {
    return Optional.of(vessels);
  }

  private FindVesselsResult(final Vessel[] vessels)
  {
    for (final Vessel v : vessels)
      this.vessels.add(v);
  }

  public static FindVesselsResult from(final Vessel[] vessels)
  {
    return new FindVesselsResult(vessels);
  }

}
