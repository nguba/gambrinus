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

import me.nguba.gambrinus.command.CreateVessel;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.onewire.OneWireAddress;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

class CreateVesselHandlerTest
{
    private CreateVessel command;

    private final Errors errors = Errors.empty();

    private CreateVesselHandler mutator;

    private final VesselRepository repo = new VesselRepository();

    private final VesselId vesselId = VesselId.of("a");

    @Test
    void alreadyExists()
    {
        repo.create(Vessel.inactive(vesselId));

        mutator.validate(command, errors);

        final ValidationFailed failed = verify();

        assertThat(failed.getErrors()
                .has(Reason
                        .from(String.format("Vessel already configured", command.getVesselId()))))
                                .isTrue();
    }

    @Test
    void cannotMountPath()
    {
        final Path           root    = Paths.get("non/existing/path");
        final OneWireAddress address = OneWireAddress.of("28.273B5D070000");

        mutator.validate(CreateVessel.from(VesselId.of("arse"), root, address), errors);
        final ValidationFailed failed = verify();

        assertThat(failed.getErrors().toString()).startsWith("Invalid sensor: ");
    }

    @Test
    void createsEntryInRepository()
    {
        mutator.changeStateFor(command);

        final Optional<Vessel> actual = repo.read(vesselId);
        assertThat(actual.isPresent()).isTrue();
    }

    @Test
    void noAddress()
    {
        mutator.validate(CreateVessel.from(null, null, null), errors);

        final ValidationFailed failed = verify();

        assertThat(failed.getErrors().has(Reason.from("OneWireAddress cannot be null"))).isTrue();
    }

    @Test
    void noRoot()
    {
        mutator.validate(CreateVessel.from(null, null, null), errors);

        final ValidationFailed failed = verify();

        assertThat(failed.getErrors().has(Reason.from("OwfsRoot cannot be null"))).isTrue();
    }

    @Test
    void noVesselId()
    {
        mutator.validate(CreateVessel.from(null, null, null), errors);

        final ValidationFailed failed = verify();

        assertThat(failed.getErrors().has(Reason.from("VesselId cannot be null"))).isTrue();
    }

    @BeforeEach
    void setUp()
    {
        mutator = new CreateVesselHandler(repo);

        command = CreateVessel.from(vesselId,
                                    Paths.get("src/test/resources/owfs"),
                                    OneWireAddress.of("28.273B5D070000"));
    }

    private ValidationFailed verify()
    {
        final ValidationFailed failed = assertThrows(ValidationFailed.class, () -> errors.verify());
        return failed;
    }

}
