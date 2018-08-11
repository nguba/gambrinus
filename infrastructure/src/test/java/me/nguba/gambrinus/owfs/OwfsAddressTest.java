package me.nguba.gambrinus.owfs;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class OwfsAddressTest
{
    private static final String VALUE = "28.4BBB68080000";

    private final OwfsAddress address = OwfsAddress.of(VALUE);

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

    @Test
    void isValid()
    {
        assertThat(address.isValid()).isTrue();
    }

    @Test
    void toStringReturnsValueAsString()
    {
        assertThat(address.toString()).isEqualTo(address.getValue().toString());
    }

    @Test
    void equalityContract()
    {
        EqualsVerifier.forClass(OwfsAddress.class).usingGetClass().verify();
    }
}
