package me.nguba.gambrinus.query.vessel;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FindVesselsTest
{
    @Test
    void equalityContract()
    {
        EqualsVerifier.forClass(FindVessels.class).verify();
    }

    @Test
    void createsValidObject()
    {
        assertThat(FindVessels.create()).isEqualTo(FindVessels.INSTANCE);
    }
}
