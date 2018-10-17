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

import me.nguba.gambrinus.command.FindVessels;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

class FindVesselsHandlerTest
{
    private FindVesselsHandler handler;

    private final VesselRepository repository = new VesselRepository();

    @Test
    void noVesselsFound()
    {
        final Set<Vessel> result = handler.query(FindVessels.create());

        assertThat(result).isEmpty();
    }

    @Test
    void returnVessels()
    {
        final Vessel[] expected = { Vessel.inactive(VesselId.of("a")),
                Vessel.inactive(VesselId.of("b")) };
        for (final Vessel v : expected)
            repository.create(v);

        final Set<Vessel> result = handler.query(FindVessels.create());

        assertThat(result).containsOnly(expected);
    }

    @BeforeEach
    void setup()
    {
        handler = FindVesselsHandler.on(repository);
    }

    @Test
    void validation()
    {
        final Errors errors = Errors.empty();
        handler.validate(FindVessels.create(), errors);

        assertThat(errors.hasErrors()).isFalse();
    }

}
