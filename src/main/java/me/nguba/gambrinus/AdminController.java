package me.nguba.gambrinus;

import me.nguba.gambrinus.equipment.Vessel;

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
    private final Administrator admin;

    private AdminController(final Administrator admin)
    {
        this.admin = admin;
    }

    @GetMapping(path = "vessel")
    public Set<Vessel> getVessels() throws Exception
    {
        return admin.findVessels();
    }
}
