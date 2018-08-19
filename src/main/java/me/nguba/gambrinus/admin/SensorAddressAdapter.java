package me.nguba.gambrinus.admin;

import me.nguba.gambrinus.onewire.OneWireAddress;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class SensorAddressAdapter
        extends ResourceAssemblerSupport<OneWireAddress, ResourceSupport>
{
    private SensorAddressAdapter()
    {
        super(AdminController.class, ResourceSupport.class);
    }

    @Override
    public ResourceSupport toResource(final OneWireAddress entity)
    {
        final ResourceSupport resource = new ResourceSupport();
        resource.add(ControllerLinkBuilder.linkTo(AdminController.class).slash("sensor")
                .slash(entity.getValue()).withSelfRel().withTitle("Maxim DS18B20"));
        return resource;
    }

    public static ResourceSupport adapt(final OneWireAddress entity)
    {
        return new SensorAddressAdapter().toResource(entity);
    }

}
