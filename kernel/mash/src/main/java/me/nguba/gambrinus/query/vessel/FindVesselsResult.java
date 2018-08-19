package me.nguba.gambrinus.query.vessel;

import me.nguba.gambrinus.cqrs.query.Result;
import me.nguba.gambrinus.equipment.Vessel;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class FindVesselsResult implements Result<Set<Vessel>>
{
    private final Set<Vessel> vessels = new HashSet<Vessel>();

    @Override
    public Optional<Set<Vessel>> getResult()
    {
        return Optional.of(vessels);
    }

    private FindVesselsResult(Vessel[] vessels)
    {
        for (Vessel v : vessels) {
            this.vessels.add(v);
        }
    }

    public static FindVesselsResult from(Vessel[] vessels)
    {
        return new FindVesselsResult(vessels);
    }

}
