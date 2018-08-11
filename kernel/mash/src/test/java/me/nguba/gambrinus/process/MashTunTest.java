package me.nguba.gambrinus.process;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MashTunTest
{
    @Test
    @DisplayName("create() generates a new unique identifier every time")
    void createUsesUniqueIdentifier()
    {
        MashTun previous = MashTun.create();

        for (int idx = 0; idx < 100; idx++) {
            final MashTun current = MashTun.create();
            assertThat(current).isNotEqualTo(previous);

            previous = current;
        }
    }

    @Test
    void fromRecreatesPreviousState()
    {
        final MashTun previous = MashTun.create();

        final MashTun current = MashTun.from(previous.getId());

        assertThat(current).isEqualTo(previous);
    }
}
