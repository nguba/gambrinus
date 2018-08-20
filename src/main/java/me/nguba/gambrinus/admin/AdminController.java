package me.nguba.gambrinus.admin;

import me.nguba.gambrinus.GambrinusOptions;
import me.nguba.gambrinus.equipment.Vessel;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

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
    public Set<Vessel> getVessels() throws Exception
    {
        return admin.findVessels();
    }

    @GetMapping(path = "sensor")
    public Set<ResourceSupport> getSensors() throws Exception
    {
        return admin.findAddresses(options.getMountpoint());
    }
}
