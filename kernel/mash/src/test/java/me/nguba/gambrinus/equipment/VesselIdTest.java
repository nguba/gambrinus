package me.nguba.gambrinus.equipment;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class VesselIdTest
{
    @Test
    void identifiersAreEqual()
    {
        assertThat(VesselId.from("Mash")).isEqualTo(VesselId.from("Mash"));
    }

    @Test
    void equalityContract()
    {
        EqualsVerifier.forClass(VesselId.class).usingGetClass().verify();
    }
}
