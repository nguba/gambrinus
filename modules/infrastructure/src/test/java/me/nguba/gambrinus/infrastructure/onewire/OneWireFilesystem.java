package me.nguba.gambrinus.infrastructure.onewire;

import java.io.File;
import java.io.FileNotFoundException;

public final class OneWireFilesystem extends AbstractOneWireFilesystem {
  
  public OneWireFilesystem(File mountpoint) throws FileNotFoundException {
    super(mountpoint, "alarm",
          "bus.0",
          "bus.1",
          "settings",
          "simultaneous",
          "statistics",
          "structure",
          "system",
          "uncached");
    
  }
}
