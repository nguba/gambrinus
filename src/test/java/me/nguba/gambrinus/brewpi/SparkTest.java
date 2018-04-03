package me.nguba.gambrinus.brewpi;

import me.nguba.gambrinus.io.SerialDevice;
import me.nguba.gambrinus.io.SerialStub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SparkTest {

  private Spark spark;

  private final SerialDevice serial = new SerialStub();

  @BeforeEach
  void setUp() {
    spark = new Spark(Integer.valueOf(1), serial);
  }

 
  @Test
  void testListenForMessages() {
    System.out.println(spark);
  }
}
