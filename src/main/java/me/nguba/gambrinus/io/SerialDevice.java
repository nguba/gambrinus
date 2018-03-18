package me.nguba.gambrinus.io;

import me.nguba.gambrinus.domain.Service;

public interface SerialDevice extends Service {

  int available();

  int read(byte[] buffer, int bytesToRead) throws InterruptedException;

  boolean hasAvailable();

}
