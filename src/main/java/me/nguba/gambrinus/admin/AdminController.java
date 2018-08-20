package me.nguba.gambrinus.admin;

import me.nguba.gambrinus.GambrinusOptions;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.onewire.OneWireAddress;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
@RestController
@RequestMapping(path = "/api/admin")
public class AdminController
{
    private final AdminService admin;

    private GambrinusOptions options;

    private AdminController(final AdminService admin, GambrinusOptions options)
    {
        this.admin = admin;
        this.options = options;
    }

    @GetMapping(path = "vessel")
    public Object getVessels() throws Exception
    {
        return admin.findVessels();
    }

    @GetMapping(path = "sensor")
    public Object getSensors() throws Exception
    {
        return admin.findAddresses(options.getMountpoint());
    }

    @PostMapping(path = "vessel/{id}/{sensor}")
    public Object createVessel(@PathVariable("id") VesselId id,
                               @PathVariable("sensor") OneWireAddress address,
                               UriComponentsBuilder builder)
            throws Exception
    {
        admin.createVessel(id, address, options.getMountpoint());
       
        UriComponents uriComponents = 
                builder.path("/vessel/{id}/{sensor}").buildAndExpand(id, address);

        return ResponseEntity.created(uriComponents.toUri()).build();
    }
}
