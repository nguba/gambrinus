package me.nguba.gambrinus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
@RestController
@RequestMapping(path = "/api/mash")
public final class MashController
{
    @GetMapping(path = "vessel")
    public void getVessels()
    {
        
    }
}
