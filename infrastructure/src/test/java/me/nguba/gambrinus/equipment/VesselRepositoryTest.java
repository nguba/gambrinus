/*
    Copyright (C) 2018  Nicolai P. Guba

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package me.nguba.gambrinus.equipment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class VesselRepositoryTest
{
    private final Vessel           mashTun    = Vessel.inactive(VesselId.of("mash"));
    private final VesselRepository repository = new VesselRepository();

    @Test
    void canReadDifferentStoredObject()
    {
        final Vessel vessel = Vessel.inactive(VesselId.of("hlt"));
        final Optional<VesselId> id = create(vessel);

        assertThat(repository.read(id.get()).get()).isEqualTo(vessel);
    }

    @Test
    void canReadStoredObject()
    {
        final Optional<VesselId> id = create(mashTun);

        assertThat(repository.read(id.get()).get()).isEqualTo(mashTun);
    }

    private Optional<VesselId> create(final Vessel vessel)
    {
        final Optional<VesselId> id = repository.create(vessel);
        return id;
    }

    @Test
    void createNull()
    {
        assertThat(repository.create(null).isPresent()).isFalse();
    }

    @Test
    void createReturnsId()
    {
        final Optional<VesselId> id = create(mashTun);

        assertThat(id.get()).isEqualTo(VesselId.of("mash"));
    }

    @Test
    void deleteNull()
    {
        repository.delete(null);
    }

    @Test
    void deleteVessel()
    {
        final Optional<VesselId> id = create(mashTun);

        repository.delete(id.get());

        assertThat(repository.read(id.get()).isPresent()).isFalse();
    }

    @Test
    void readAll()
    {
        final Vessel[] expected = { Vessel.inactive(VesselId.of("1")),
                Vessel.inactive(VesselId.of("2")) };
        for (final Vessel v : expected)
            repository.create(v);

        final Vessel[] actual = repository.findAll();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void readNonExistentObject()
    {
        assertThat(repository.read(VesselId.of("none")).isPresent()).isFalse();
    }

    @Test
    void readNull()
    {
        assertThat(repository.read(null).isPresent()).isFalse();
    }

    @Test
    void updateNotSupported()
    {
        assertThrows(UnsupportedOperationException.class, () -> repository.update(mashTun));
    }
}
