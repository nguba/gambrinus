package me.nguba.gambrinus.admin;

import me.nguba.gambrinus.onewire.OneWireAddress;

import org.springframework.hateoas.ResourceSupport;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class SensorAddressResource extends ResourceSupport
{
    private final String id;

    public SensorAddressResource(final OneWireAddress address)
    {
        this.id = address.getValue();
    }

    public static SensorAddressResource of(final OneWireAddress addresses)
    {
        return new SensorAddressResource(addresses);
    }

}
