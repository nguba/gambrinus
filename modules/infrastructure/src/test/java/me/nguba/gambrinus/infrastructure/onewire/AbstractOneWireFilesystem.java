package me.nguba.gambrinus.infrastructure.onewire;

import java.io.File;
import java.io.FileNotFoundException;

public abstract class AbstractOneWireFilesystem {

  private File mountpoint;

  protected AbstractOneWireFilesystem(File mountpoint, String... entries) throws FileNotFoundException {
    this.mountpoint = mountpoint;
    if (!mountpoint.exists()) {
      throw new FileNotFoundException(mountpoint.getAbsolutePath());
    }
    createEntries(entries);
  }
  
  protected void createEntries(String...entries) {
    for (String entry : entries) {
      File f = new File(mountpoint, entry);
      f.mkdir();
    }
  }
}
