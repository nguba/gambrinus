package me.nguba.gambrinus;

import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.owfs.OwfsRoot;
import me.nguba.gambrinus.owfs.OwfsSensor;

public enum ApplicationMother
{
  ;

  public static Vessel mashTun()
  {
    return Vessel.of(VesselId.of("mash"),
                     OwfsSensor.from(OwfsRoot.test(),
                                     OneWireAddress.of("28.273B5D070000")));
  }
}
