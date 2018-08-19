package me.nguba.gambrinus.query.onewire;

import me.nguba.gambrinus.cqrs.query.Result;
import me.nguba.gambrinus.onewire.OneWireAddress;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class FindOneWireAddressResult implements Result<Set<OneWireAddress>>
{
    private final Set<OneWireAddress> result;

    private FindOneWireAddressResult(final Set<OneWireAddress> result)
    {
        this.result = result;
    }

    @Override
    public Optional<Set<OneWireAddress>> getResult()
    {
        return Optional.of(result);
    }

    public static FindOneWireAddressResult from(final OneWireAddress[] addresses)
    {
        if (addresses == null) {
            return new FindOneWireAddressResult(new HashSet<>());
        }

        final Set<OneWireAddress> result = new HashSet<>();
        for (final OneWireAddress address : addresses) {
            result.add(address);
        }
        return new FindOneWireAddressResult(result);
    }
}
