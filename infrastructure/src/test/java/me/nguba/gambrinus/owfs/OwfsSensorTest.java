package me.nguba.gambrinus.owfs;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class OwfsSensorTest
{
    private final OwfsAddress address = OwfsAddress.of("28.4BBB68080000");
    
    @Test
    void testGetId()
    {
        assertThat(OwfsSensor.of(address).getId()).isEqualTo(address);
    }

}
