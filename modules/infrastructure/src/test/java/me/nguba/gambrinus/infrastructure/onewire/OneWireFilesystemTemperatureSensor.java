package me.nguba.gambrinus.infrastructure.onewire;

import java.io.File;
import java.io.FileNotFoundException;

public class OneWireFilesystemTemperatureSensor extends AbstractOneWireFilesystem {

  protected OneWireFilesystemTemperatureSensor(File mountpoint)
      throws FileNotFoundException {
    super(mountpoint,
          "address",
          "alias",
          "crc8",
          "errata",
          "family",
          "fasttemp",
          "id",
          "latesttemp",
          "locator",
          "power",
          "r_address",
          "r_id",
          "r_locator",
          "scratchpad",
          "temperature",
          "temperature10",
          "temperature11",
          "temperature12",
          "temperature9",
          "temphigh",
          "templow",
          "tempres",
          "type");
  }

}
