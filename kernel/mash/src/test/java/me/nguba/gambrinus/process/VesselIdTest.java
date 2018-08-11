package me.nguba.gambrinus.process;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class VesselIdTest
{
    @Test
    void generatesUniqueIdentifiers()
    {
        assertThat(VesselId.generate()).isNotEqualTo(VesselId.generate());
    }
}
