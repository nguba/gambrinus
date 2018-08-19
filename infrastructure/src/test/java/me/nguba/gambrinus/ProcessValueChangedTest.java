package me.nguba.gambrinus;

import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.process.Temperature;
import nl.jqno.equalsverifier.EqualsVerifier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class ProcessValueChangedTest
{
    private final Temperature expected = Temperature.celsius(29);

    private final OneWireAddress address = OneWireAddress.of("28.273B5D070000");

    @Test
    void failsWhenNoVesselGiven()
    {
        assertThrows(IllegalArgumentException.class,
                     () -> ProcessValueChanged.on(null, expected));
    }

    @Test
    void processValue()
    {
        assertThat(ProcessValueChanged.on(address, expected).getProcessValue())
                .isEqualTo(expected);
    }

    @Test
    void processValueNull()
    {
        assertThat(ProcessValueChanged.on(address, null).getProcessValue())
                .isEqualTo(Temperature.celsius(0));
    }

    @Test
    void vesselId()
    {
        assertThat(ProcessValueChanged.on(address, expected).getAddress()).isEqualTo(address);
    }

    @Test
    void equalityContract()
    {
        EqualsVerifier.forClass(ProcessValueChanged.class).usingGetClass().verify();
    }
}
