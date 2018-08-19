package me.nguba.gambrinus.query.onewire;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FindOneWireAddressesTest
{

    @Test
    void equalityContract()
    {
        EqualsVerifier.forClass(FindOneWireAddresses.class).usingGetClass().verify();
    }

    @Test
    void recordsMountpoint()
    {
        assertThat(FindOneWireAddresses.on("src/main").getMountpoint()).isEqualTo("src/main");
    }
}
