package me.nguba.gambrinus.equipment;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class VesselIdTest
{
    @Test
    void identifiersAreEqual()
    {
        assertThat(VesselId.of("Mash")).isEqualTo(VesselId.of("Mash"));
    }

    @Test
    void equalityContract()
    {
        EqualsVerifier.forClass(VesselId.class).usingGetClass().verify();
    }
}
