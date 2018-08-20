package me.nguba.gambrinus.admin;

import me.nguba.gambrinus.equipment.Vessel;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public class VesselAdapter extends ResourceAssemblerSupport<Vessel, ResourceSupport>
{
    public VesselAdapter()
    {
        super(AdminController.class, ResourceSupport.class);
    }

    @Override
    public ResourceSupport toResource(final Vessel entity)
    {
        final ResourceSupport resource = new ResourceSupport();
        resource.add(ControllerLinkBuilder.linkTo(AdminController.class).slash("vessel")
                .slash(entity.getId()).slash(entity.address()).withSelfRel()
                .withTitle(entity.getId().toString()));
        return resource;
    }

    public static ResourceSupport adapt(final Vessel entity)
    {
        return new VesselAdapter().toResource(entity);
    }

}
