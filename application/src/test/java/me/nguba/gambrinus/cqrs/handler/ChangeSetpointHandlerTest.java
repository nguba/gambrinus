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

import me.nguba.gambrinus.command.ChangeSetpoint;
import me.nguba.gambrinus.cqrs.handler.ChangeSetpointHandler;
import me.nguba.gambrinus.ddd.validation.Errors;
import me.nguba.gambrinus.ddd.validation.Reason;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.process.Temperature;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class ChangeSetpointHandlerTest
{
    private final VesselId         id       = VesselId.of("mash");
    private ChangeSetpointHandler  mutator;
    private final VesselRepository repo     = new VesselRepository();
    private final Temperature      setpoint = Temperature.celsius(68);

    @Test
    void mutateMofifiesSetpoint()
    {
        repo.create(Vessel.inactive(id));

        mutator.changeStateFor(ChangeSetpoint.on(id, setpoint));

        assertThat(repo.read(id).get().setpoint()).isEqualTo(Temperature.celsius(68));
    }

    @Test
    void mutateNonExistingVessel()
    {
        assertThrows(IllegalArgumentException.class,
                     () -> mutator.changeStateFor(ChangeSetpoint.on(id, setpoint)));
    }

    @Test
    void mutateNullCommand()
    {
        assertThrows(IllegalArgumentException.class, () -> mutator.changeStateFor(null));
    }

    @BeforeEach
    void setUp()
    {
        mutator = ChangeSetpointHandler.from(repo);
    }

    @Test
    void validation() throws Exception
    {
        final Errors results = Errors.empty();

        mutator.validate(ChangeSetpoint.on(null, null), results);

        final ValidationFailed exception = assertThrows(ValidationFailed.class,
                                                        () -> results.verify());

        assertThat(exception.getErrors().has(Reason.from("No vesselId"))).isTrue();
        assertThat(exception.getErrors().has(Reason.from("No setpoint"))).isTrue();
    }
}
