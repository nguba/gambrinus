package me.nguba.gambrinus.owfs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.process.Temperature;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class OwfsSensorTest
{
  private OwfsSensor sensor;

  @BeforeEach
  void setUp() throws Exception
  {
    sensor = OwfsSensor.from(OwfsMother.root(), OwfsMother.address());
  }

  @Test
  void mountFailure() throws Exception
  {
    assertThat(OwfsSensor.from(OwfsRoot.of("foo"), OwfsMother.address()).isValid()).isFalse();
  }

  @Test
  void read() throws Exception
  {
    final Temperature t1 = sensor.read().get();
    final Temperature expected = Temperature.celsius(25.7);

    assertThat(t1).isEqualTo(expected);
  }

  @Test
  void readMultiple() throws Exception
  {
    final Temperature t1 = sensor.read().get();
    final Temperature t2 = sensor.read().get();

    assertThat(t1).isEqualTo(t2);
  }

  @Test
  void readFromEmptyTemperatureFile() throws Exception
  {
    final OwfsSensor empty = OwfsSensor.from(OwfsRoot.of("src/test/resources/emptyfs"),
                                             OwfsMother.address());

    final Optional<Temperature> temperature = empty.read();

    assertThat(temperature.isPresent()).isFalse();
  }
}
