package me.nguba.gambrinus;

import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.process.Temperature;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class ProcessValueChangedTest
{
    private final Temperature expected = Temperature.celsius(29);

    private final VesselId vesselId = VesselId.of("some vessel");

    @Test
    void failsWhenNoVesselGiven()
    {
        assertThrows(IllegalArgumentException.class,
                     () -> ProcessValueChanged.on(null, expected));
    }

    @Test
    void processValue()
    {
        assertThat(ProcessValueChanged.on(vesselId, expected).getProcessValue())
                .isEqualTo(expected);
    }

    @Test
    void processValueNull()
    {
        assertThat(ProcessValueChanged.on(vesselId, null).getProcessValue())
                .isEqualTo(Temperature.celsius(0));
    }

    @Test
    void vesselId()
    {
        assertThat(ProcessValueChanged.on(vesselId, expected).getVesselId()).isEqualTo(vesselId);
    }

}
