package me.nguba.gambrinus.query.onewire;

import me.nguba.gambrinus.cqrs.query.QueryHandler;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.owfs.OwfsRoot;
import me.nguba.gambrinus.owfs.OwfsSensor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class FindOneWireAddressesHandler
        implements QueryHandler<FindOneWireAddresses, FindOneWireAddressResult>
{
    @Override
    public void validate(final FindOneWireAddresses query, final Errors errors)
    {
        final OwfsRoot root = OwfsRoot.of(query.getMountpoint());
        if (!root.isValid()) {
            errors.add(Reason.from(String.format("Invalid mountpoint: %s", root)));
        }
    }

    @Override
    public FindOneWireAddressResult run(final FindOneWireAddresses query)
    {
        final OwfsRoot root = OwfsRoot.of(query.getMountpoint());
        final Set<OneWireAddress> addresses = new HashSet<>();

        for (final OwfsSensor sensor : root.listSensors()) {
            addresses.add(sensor.getId());
        }
        return FindOneWireAddressResult
                .from(addresses.toArray(new OneWireAddress[addresses.size()]));
    }

}
