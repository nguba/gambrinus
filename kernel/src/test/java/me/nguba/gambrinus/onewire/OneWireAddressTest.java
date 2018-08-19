package me.nguba.gambrinus.onewire;

import me.nguba.gambrinus.SingleValueObjectFixture;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class OneWireAddressTest extends SingleValueObjectFixture<String, OneWireAddress>
{
    @Test
    void isNull()
    {
        assertThat(OneWireAddress.of(null).isValid()).isFalse();
    }

    @Test
    void isEmpty()
    {
        assertThat(OneWireAddress.of("").isValid()).isFalse();
    }

    @Test
    void isShort()
    {
        final String shortAddress = getValueObject().getValue().substring(1);
        assertThat(OneWireAddress.of(shortAddress).isValid()).isFalse();
    }

    @Override
    protected OneWireAddress makeValueObject()
    {
        return OneWireAddress.of("28.4BBB68080000");
    }
}
