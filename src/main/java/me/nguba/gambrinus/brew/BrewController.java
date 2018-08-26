package me.nguba.gambrinus.brew;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.nguba.gambrinus.Brewmaster;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.process.Temperature;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
@RestController
@RequestMapping(path = "/api/brew")
public final class BrewController
{
  private final Brewmaster brewmaster;

  private BrewController(final Brewmaster brewmaster)
  {
    this.brewmaster = brewmaster;
  }

  @PutMapping("/heat/{vesselId}/{temperature}")
  public void heat(@PathVariable("vesselId") final String vesselId,
                   @PathVariable("temperature") final double temperature)
      throws ValidationFailed
  {
    System.out.println(vesselId);
    System.out.println(temperature);

    brewmaster.heat(VesselId.of(vesselId), Temperature.celsius(temperature));
  }
}
