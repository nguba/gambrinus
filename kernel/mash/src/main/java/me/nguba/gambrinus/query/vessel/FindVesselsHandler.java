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

    public FindVesselsHandler(VesselRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public void validate(FindVessels query, Errors errors)
    {
    }

    @Override
    public FindVesselsResult run(FindVessels query)
    {
        return FindVesselsResult.from(repository.findAll());
    }
}
