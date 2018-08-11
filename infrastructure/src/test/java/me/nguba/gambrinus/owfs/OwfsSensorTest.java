package me.nguba.gambrinus.owfs;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class OwfsSensorTest
{
    @Test
    void mountFailure() throws Exception
    {
        IOException exception = assertThrows(IOException.class,
                                             () -> OwfsSensor.mount(OwfsRoot.of("foo"),
                                                                    OwfsMother.address()));

        assertThat(exception.getMessage()).startsWith("Mountpoint does not exist:");
    }

    @Test
    void mount() throws Exception
    {
        OwfsSensor sensor = OwfsSensor.mount(OwfsMother.root(), OwfsMother.address());
        assertThat(sensor.getMount())
                .isEqualTo(OwfsMount.from(OwfsMother.root(), OwfsMother.address()));
    }

}
