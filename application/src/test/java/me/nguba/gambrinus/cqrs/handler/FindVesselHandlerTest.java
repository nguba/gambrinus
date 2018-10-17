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
package me.nguba.gambrinus.cqrs.handler;

import me.nguba.gambrinus.command.FindVessel;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 *
 */
class FindVesselHandlerTest
{
    private FindVesselHandler handler;

    private final VesselRepository vessels = new VesselRepository();

    @Test
    void result()
    {
        final Vessel expected = Vessel.inactive(VesselId.of("HLT"));
        vessels.create(expected);

        final Optional<Vessel> result = handler.query(FindVessel.of(VesselId.of("HLT")));
        assertThat(result.get()).isEqualTo(expected);
    }

    @Test
    void resultNotFound()
    {
        final Optional<Vessel> result = handler.query(FindVessel.of(VesselId.of("HLT")));
        assertThat(result.isPresent()).isFalse();
    }

    @BeforeEach
    void setUp()
    {
        handler = FindVesselHandler.on(vessels);
    }

    @Test
    void validation()
    {
        final Errors errors = Errors.empty();

        handler.validate(FindVessel.of(null), errors);

        final ValidationFailed failed = assertThrows(ValidationFailed.class, () -> errors.verify());
        assertThat(failed.getErrors().toString()).contains("vesselId cannot be null");
    }

}
