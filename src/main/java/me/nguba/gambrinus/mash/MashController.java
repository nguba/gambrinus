package me.nguba.gambrinus.mash;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.nguba.gambrinus.Brewmaster;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
@RestController
@RequestMapping(path = "/api/mash")
public final class MashController
{
  private MashController(final Brewmaster brewmaster)
  {
  }
}
