package me.nguba.gambrinus.owfs;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class OwfsAddressTest extends SingleValueObjectFixture<String, OwfsAddress>
{
    private static final String VALUE = "28.4BBB68080000";

    @Test
    void isNull()
    {
        assertThat(OwfsAddress.of(null).isValid()).isFalse();
    }

    @Test
    void isEmpty()
    {
        assertThat(OwfsAddress.of("").isValid()).isFalse();
    }

    @Test
    void isShort()
    {
        assertThat(OwfsAddress.of(VALUE.substring(1)).isValid()).isFalse();
    }

    @Override
    OwfsAddress makeValueObject()
    {
        return OwfsAddress.of(VALUE);
    }
}
