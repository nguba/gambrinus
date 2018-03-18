package me.nguba.gambrinus.io;

public interface SerialDevice {

  int available();

  int read(byte[] buffer, int bytesToRead) throws InterruptedException;

  boolean hasAvailable();
}