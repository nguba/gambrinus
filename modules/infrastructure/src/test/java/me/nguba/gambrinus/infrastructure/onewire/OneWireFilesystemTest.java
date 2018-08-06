package me.nguba.gambrinus.infrastructure.onewire;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class OneWireFilesystemTest {

  private final File mountpoint = new File("src/test/resources/owfs");
 
  @Test
  @DisplayName("Error when mountpoint doesn't exists")
  void mountpointCheck() {
    assertThrows(FileNotFoundException.class,
                 () -> new OneWireFilesystem(new File(mountpoint, "notHere")));
  }

  @Test
  @SuppressWarnings("unused")
  @DisplayName("Creates toplevel entries")
  void createEntries() throws Exception {
    
    new OneWireFilesystem(mountpoint);
    
    final String[] expected = {
        "alarm",
        "bus.0",
        "bus.1",
        "settings",
        "simultaneous",
        "statistics",
        "structure",
        "system",
        "uncached" };
    
    for(String directory : expected) {
      assertTrue(new File(mountpoint, directory).exists());
    }
  }
 
}
