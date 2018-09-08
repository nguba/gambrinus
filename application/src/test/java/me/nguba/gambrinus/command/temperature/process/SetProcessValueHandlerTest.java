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
package me.nguba.gambrinus.command.temperature.process;

import me.nguba.gambrinus.command.SetProcessValue;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.owfs.OwfsRoot;
import me.nguba.gambrinus.owfs.OwfsSensor;
import me.nguba.gambrinus.process.Temperature;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 *
 */
class SetProcessValueHandlerTest
{

    private SetProcessValueHandler handler;

    private final VesselId id = VesselId.of("bk");

    private final Temperature processValue = Temperature.celsius(68.0);

    private final VesselRepository repo = new VesselRepository();

    @Test
    void mutateNonExistingVessel()
    {
        assertThrows(IllegalArgumentException.class,
                     () -> handler.changeStateFor(SetProcessValue.with(id, processValue)));
    }

    @Test
    void mutateNullCommand()
    {
        assertThrows(IllegalArgumentException.class, () -> handler.changeStateFor(null));
    }

    @Test
    void mutateReadsFromFilesystem()
    {
        repo.create(Vessel.of(id, OwfsSensor.from(OwfsRoot.test(), OneWireAddress.defaultMash())));

        handler.changeStateFor(SetProcessValue.with(id, processValue));

        assertThat(repo.read(id).get().processValue()).isEqualTo(processValue);
    }

    @BeforeEach
    void setUp()
    {
        handler = SetProcessValueHandler.from(repo);
    }

    @Test
    void validateVesselExists() throws Exception
    {
        final Errors results = Errors.empty();

        handler.validate(SetProcessValue.with(id, processValue), results);

        final ValidationFailed exception = assertThrows(ValidationFailed.class,
                                                        () -> results.verify());

        assertThat(exception.getErrors().has(Reason.from("Vessel not found: bk"))).isTrue();
    }

    @Test
    void validation() throws Exception
    {
        final Errors results = Errors.empty();

        handler.validate(SetProcessValue.with(null, null), results);

        final ValidationFailed exception = assertThrows(ValidationFailed.class,
                                                        () -> results.verify());

        assertThat(exception.getErrors().has(Reason.from("No vesselId"))).isTrue();
        assertThat(exception.getErrors().has(Reason.from("No processValue"))).isTrue();
    }
}
