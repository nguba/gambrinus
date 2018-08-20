package me.nguba.gambrinus.equipment;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class VesselRepositoryTest
{
    private final VesselRepository repository = new VesselRepository();
    private final Vessel           mashTun    = Vessel.inactive(VesselId.of("mash"));

    @Test
    void createReturnsId()
    {
        final Optional<VesselId> id = create(mashTun);

        assertThat(id.get()).isEqualTo(VesselId.of("mash"));
    }

    private Optional<VesselId> create(final Vessel vessel)
    {
        final Optional<VesselId> id = repository.create(vessel);
        return id;
    }

    @Test
    void canReadStoredObject()
    {
        final Optional<VesselId> id = create(mashTun);

        assertThat(repository.read(id.get()).get()).isEqualTo(mashTun);
    }

    @Test
    void canReadDifferentStoredObject()
    {
        final Vessel vessel = Vessel.inactive(VesselId.of("hlt"));
        final Optional<VesselId> id = create(vessel);

        assertThat(repository.read(id.get()).get()).isEqualTo(vessel);
    }

    @Test
    void readNonExistentObject()
    {
        assertThat(repository.read(VesselId.of("none")).isPresent()).isFalse();
    }

    @Test
    void updateNotSupported()
    {
        assertThrows(UnsupportedOperationException.class, () -> repository.update(mashTun));
    }

    @Test
    void deleteVessel()
    {
        final Optional<VesselId> id = create(mashTun);

        repository.delete(id.get());

        assertThat(repository.read(id.get()).isPresent()).isFalse();
    }

    @Test
    void deleteNull()
    {
        repository.delete(null);
    }

    @Test
    void readNull()
    {
        assertThat(repository.read(null).isPresent()).isFalse();
    }

    @Test
    void createNull()
    {
        assertThat(repository.create(null).isPresent()).isFalse();
    }

    @Test
    void readAll()
    {
        final Vessel[] expected = { Vessel.inactive(VesselId.of("1")),
                Vessel.inactive(VesselId.of("2")) };
        for (final Vessel v : expected) {
            repository.create(v);
        }

        final Vessel[] actual = repository.findAll();

        assertThat(actual).isEqualTo(expected);
    }
}
