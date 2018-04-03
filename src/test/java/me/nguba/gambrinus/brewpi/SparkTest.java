package me.nguba.gambrinus.brewpi;

import me.nguba.gambrinus.brewpi.serialization.SparkSerializerService;
import me.nguba.gambrinus.hardware.Function;
import me.nguba.gambrinus.hardware.onewire.OneWireTemperatureSensor;
import me.nguba.gambrinus.io.SerialStub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

class SparkTest {

  private Spark spark;

  private final SerialStub serial = new SerialStub();

  private final SparkSerializerService serializer = new SparkSerializerService();

  @BeforeEach
  void setUp() {
    spark = new Spark(Integer.valueOf(1), serial);
  }

  @Test
  void testListenForMessages() {
    System.out.println(spark);
  }

  @Test
  @Disabled("WIP")
  void listDevices() throws Exception {
    final String json = serializer.toJson(BrewPiMother.availableDevices());
    serial.write(json);

    Map<Function, OneWireTemperatureSensor> available = spark.listProbes();
    
    assertNotNull(available);

  }
}
