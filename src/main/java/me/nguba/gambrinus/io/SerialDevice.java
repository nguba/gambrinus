package me.nguba.gambrinus.io;

import me.nguba.gambrinus.domain.Service;

import java.nio.ByteBuffer;

public interface SerialDevice extends Service {

  int available();

  int read(ByteBuffer buffer);

  boolean hasAvailable();

  int write(ByteBuffer buffer);

}
