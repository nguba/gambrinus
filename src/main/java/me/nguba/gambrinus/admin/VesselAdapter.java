package me.nguba.gambrinus.admin;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import me.nguba.gambrinus.equipment.Vessel;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 * 
 */
public class VesselAdapter extends ResourceAssemblerSupport<Vessel, VesselResource>
{
  public VesselAdapter()
  {
    super(AdminController.class, VesselResource.class);
  }

  @Override
  public VesselResource toResource(final Vessel entity)
  {
    final VesselResource resource = new VesselResource(entity);
 
    resource.add(ControllerLinkBuilder.linkTo(AdminController.class).slash("vessel")
        .slash(entity.getId()).withSelfRel()
        .withTitle(entity.getId().toString()));
   
    return resource;
  }

  public static VesselResource adapt(final Vessel entity)
  {
    return new VesselAdapter().toResource(entity);
  }

}
