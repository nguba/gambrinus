package me.nguba.gambrinus.brewpi;

import me.nguba.gambrinus.brewpi.serialization.SparkSerializerService;
import me.nguba.gambrinus.domain.Entity;
import me.nguba.gambrinus.domain.hardware.onewire.OneWireAddress;
import me.nguba.gambrinus.domain.hardware.onewire.OneWireTemperatureSensor;
import me.nguba.gambrinus.io.SerialDevice;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Spark implements Entity<Integer> {

  private Integer                                       id;
  private SerialDevice                                  serialDevice;
  private Map<OneWireAddress, OneWireTemperatureSensor> sensors = new ConcurrentHashMap<>();

  private final SparkSerializerService serializer = new SparkSerializerService();
  
  private final StringBuilder outputBuffer = new StringBuilder();
  
  public Spark(Integer id, SerialDevice serialDevice) {
    this.id = id;
    this.serialDevice = serialDevice;
  }

  @Override
  public Integer id() {
    return id;
  }

  public void pollAvailableDevices() {
  }

  public void listenForMessages() {
  }

  public void send(final SparkRequest request) throws IOException {

    final String message = serializer.toJson(request);
    serialDevice.write(ByteBuffer.wrap(message.getBytes()));

  }

  public SparkResponse receive() throws IOException {
    while (serialDevice.hasAvailable()) {
      final ByteBuffer in = ByteBuffer.allocate(serialDevice.available());
      serialDevice.read(in);

      in.flip();

      while (in.hasRemaining()) {
        outputBuffer.append((char) in.get());
      }
    }
    return serializer.toAvailable(outputBuffer.toString());
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Spark other = (Spark) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Spark [id=").append(id).append(", serialDevice=").append(serialDevice)
        .append("]");
    return builder.toString();
  }
}
