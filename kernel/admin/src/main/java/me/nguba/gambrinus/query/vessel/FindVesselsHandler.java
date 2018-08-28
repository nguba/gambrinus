package me.nguba.gambrinus.query.vessel;

import me.nguba.gambrinus.cqrs.query.QueryHandler;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.equipment.VesselRepository;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class FindVesselsHandler implements QueryHandler<FindVessels, FindVesselsResult>
{
  private final VesselRepository repository;

  private FindVesselsHandler(final VesselRepository repository)
  {
    this.repository = repository;
  }

  public static FindVesselsHandler on(final VesselRepository repository)
  {
    return new FindVesselsHandler(repository);
  }

  @Override
  public void validate(final FindVessels query, final Errors errors)
  {
  }

  @Override
  public FindVesselsResult query(final FindVessels query)
  {
    return FindVesselsResult.from(repository.findAll());
  }
}
