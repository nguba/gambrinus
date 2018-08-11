package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.process.Temperature;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class OwfsSensorTest
{
    private OwfsSensor sensor;

    @BeforeEach
    void setUp() throws Exception
    {
        sensor = OwfsSensor.mount(OwfsMother.root(), OwfsMother.address());
    }

    @Test
    void mountFailure() throws Exception
    {
        final IOException exception = assertThrows(IOException.class,
                                                   () -> OwfsSensor.mount(OwfsRoot.of("foo"),
                                                                          OwfsMother.address()));

        assertThat(exception.getMessage()).startsWith("Mountpoint does not exist:");
    }

    @Test
    void mount() throws Exception
    {
        assertThat(sensor.getMount())
                .isEqualTo(OwfsMount.from(OwfsMother.root(), OwfsMother.address()));
    }

    @Test
    void read() throws Exception
    {
        final Temperature t1 = sensor.read();
        final Temperature expected = Temperature.celsius(25.7);

        assertThat(t1).isEqualTo(expected);
    }

    @Test
    void readMultiple() throws Exception
    {
        final Temperature t1 = sensor.read();
        final Temperature t2 = sensor.read();

        assertThat(t1).isEqualTo(t2);
    }

    @Test
    void readFromDefectFs() throws Exception
    {
        final OwfsSensor defect = OwfsSensor.mount(OwfsRoot.of("src/test/resources/defectfs"),
                                                   OwfsMother.address());

        assertThrows(IOException.class, () -> defect.read());
    }

    @Test
    void readFromEmptyFs() throws Exception
    {
        final OwfsSensor empty = OwfsSensor.mount(OwfsRoot.of("src/test/resources/emptyfs"),
                                                  OwfsMother.address());

        final Temperature temperature = empty.read();

        assertThat(temperature).isNull();
    }
}
