package me.nguba.gambrinus.owfs;

import me.nguba.gambrinus.SingleValueObjectFixture;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class OwfsAddressTest extends SingleValueObjectFixture<String, OwfsAddress>
{
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
        final String shortAddress = getValueObject().getValue().substring(1);
        assertThat(OwfsAddress.of(shortAddress).isValid()).isFalse();
    }

    @Override
    protected OwfsAddress makeValueObject()
    {
        return OwfsMother.address();
    }
}
