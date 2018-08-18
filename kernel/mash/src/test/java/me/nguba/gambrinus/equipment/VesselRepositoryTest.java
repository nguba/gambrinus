package me.nguba.gambrinus.equipment;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import javax.naming.OperationNotSupportedException;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class VesselRepositoryTest
{
    private final VesselRepository repository = new VesselRepository();
    private final Vessel           mashTun    = Vessel.of(VesselId.of("mash"));

    @Test
    void createReturnsId()
    {
        Optional<VesselId> id = create(mashTun);

        assertThat(id.get()).isEqualTo(VesselId.of("mash"));
    }

    private Optional<VesselId> create(Vessel vessel)
    {
        Optional<VesselId> id = repository.create(vessel);
        return id;
    }

    @Test
    void canReadStoredObject()
    {
        Optional<VesselId> id = create(mashTun);

        assertThat(repository.read(id.get()).get()).isEqualTo(mashTun);
    }

    @Test
    void canReadDifferentStoredObject()
    {
        Vessel vessel = Vessel.of(VesselId.of("hlt"));
        Optional<VesselId> id = create(vessel);

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
        Optional<VesselId> id = create(mashTun);

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
}
