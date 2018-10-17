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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.nguba.gambrinus.command.ReadTemperature;
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

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class ReadTemperatureHandlerTest
{
    private ReadTemperatureHandler handler;

    private final Errors results = Errors.empty();

    private final VesselId vesselId = VesselId.of("boil");

    private final VesselRepository vessels = new VesselRepository();

    @Test
    void failOnNoVesselId()
    {
        handler.validate(ReadTemperature.from(null), results);

        final ValidationFailed failed = assertThrows(ValidationFailed.class,
                                                     () -> results.verify());

        assertThat(failed.getErrors().has(Reason.from("No vesselId")));
    }

    @Test
    void failOnVesselInactive()
    {
        handler.validate(ReadTemperature.from(vesselId), results);

        final ValidationFailed failed = assertThrows(ValidationFailed.class,
                                                     () -> results.verify());

        assertThat(failed.getErrors().has(Reason.from("No sensor configured for: boil")));
    }

    @Test
    void failOnVesselNotFound()
    {
        handler.validate(ReadTemperature.from(vesselId), results);

        final ValidationFailed failed = assertThrows(ValidationFailed.class,
                                                     () -> results.verify());

        assertThat(failed.getErrors().has(Reason.from("Vessel not found: boil")));
    }

    @BeforeEach
    void setUp()
    {
        handler = ReadTemperatureHandler.on(vessels);
    }

    @Test
    void temperatureResult()
    {
        vessels.create(Vessel
                .of(vesselId,
                    OwfsSensor.from(OwfsRoot.test(), OneWireAddress.of("28.273B5D070000"))));

        final Optional<Temperature> result = handler.query(ReadTemperature.from(vesselId));

        assertThat(result.get()).isEqualTo(Temperature.celsius(33.5));
    }

}
