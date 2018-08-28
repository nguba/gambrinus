package me.nguba.gambrinus.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import me.nguba.gambrinus.GambrinusOptions;
import me.nguba.gambrinus.RaspberryPinOptions;
import me.nguba.gambrinus.WebMvcUtil;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.onewire.OneWireAddress;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 *
 */
@RestController
@RequestMapping(path = "/api/admin")
public class AdminController
{
  private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

  private final AdminResourceService admin;

  private final GambrinusOptions options;

  private AdminController(final AdminResourceService admin,
                          final GambrinusOptions options,
                          final RaspberryPinOptions pins)
  {
    this.admin = admin;
    this.options = options;

    LOGGER.info("{}", pins);
  }

  @GetMapping(path = "vessel")
  public Object getVessels() throws Exception
  {
    return admin.findVessels();
  }

  @GetMapping(path = "vessel/{name}")
  public Object getVessel(@PathVariable("name") final String name) throws Exception
  {
    return admin.findVessel(VesselId.of(name));
  }

  @GetMapping(path = "sensor")
  public Object getSensors() throws Exception
  {
    return admin.findAddresses(options.getMountpoint());
  }

  @PostMapping(path = "vessel/{id}/{sensor}")
  public Object createVessel(@PathVariable("id") final VesselId id,
                             @PathVariable("sensor") final OneWireAddress address,
                             final UriComponentsBuilder builder)
      throws Exception
  {
    admin.createVessel(id, address, options.getMountpoint());

    return WebMvcUtil.created(builder.path("/vessel/{id}/{sensor}"), id, address);
  }
}
