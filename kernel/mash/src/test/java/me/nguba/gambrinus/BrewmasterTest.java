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
package me.nguba.gambrinus;

import me.nguba.gambrinus.command.temperature.setpoint.SetpointChanged;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.Vessel;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.event.EventPublisher;
import me.nguba.gambrinus.event.MutatorEvent;
import me.nguba.gambrinus.onewire.OneWireAddress;
import me.nguba.gambrinus.owfs.OwfsRoot;
import me.nguba.gambrinus.owfs.OwfsSensor;
import me.nguba.gambrinus.process.Temperature;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
class BrewmasterTest implements EventPublisher
{
    private final VesselRepository vessels = new VesselRepository();

    private Brewmaster brewmaster;

    private final VesselId vesselId = VesselId.of("mash");

    private Optional<MutatorEvent> currentEvent;

    @BeforeEach
    void setUp()
    {
        vessels.create(Vessel.of(vesselId,
                                 OwfsSensor.from(OwfsRoot.test(), OneWireAddress.defaultMash())));

        final BrewCommands commandFactory = new BrewCommands(vessels, this);
        final BrewQueries queryFactory = new BrewQueries(vessels);

        brewmaster = new Brewmaster(commandFactory, queryFactory);
    }

    @Test
    void readTemperartureNotMonitored() throws Exception
    {
        vessels.create(Vessel.of(vesselId,
                                 OwfsSensor.from(OwfsRoot.test(), OneWireAddress.empty())));
        
        assertThrows(IllegalStateException.class, () -> brewmaster.processValue(vesselId));
    }
    
    @Test
    void readTemperarture() throws Exception
    {
        brewmaster.monitor(vesselId, Period.oneSecond());
        final Temperature temperature = brewmaster.processValue(vesselId);
        assertThat(temperature).isEqualTo(Temperature.celsius(25.7));
    }

    @Test
    void readTemperartureFromInactiveVessel() throws Exception
    {
        vessels.create(Vessel.of(vesselId,
                                 OwfsSensor.from(OwfsRoot.test(), OneWireAddress.empty())));

        assertThrows(IllegalStateException.class, () -> brewmaster.processValue(vesselId));
    }

    @Test
    void heatUpdatesSetpoint() throws Exception
    {
        brewmaster.heat(vesselId, Temperature.celsius(65.5));
        assertThat(vessels.read(vesselId).get().setpoint()).isEqualTo(Temperature.celsius(65.5));
    }

    @Test
    void heatBroadcastsSetpoint() throws Exception
    {
        brewmaster.heat(vesselId, Temperature.celsius(65.5));

        final SetpointChanged event = (SetpointChanged) currentEvent.get();

        assertThat(event.getSetpoint()).isEqualTo(Temperature.celsius(65.5));
        assertThat(event.getVesselId()).isEqualTo(vesselId);

    }

    @Override
    public <E extends MutatorEvent> void publish(final E event)
    {
        currentEvent = Optional.of(event);
    }

    @Override
    public void subscribe(final Object recipient)
    {
    }

    @Test
    void monitorVessel() throws ValidationFailed
    {
        vessels.create(Vessel.of(vesselId,
                                 OwfsSensor.from(OwfsRoot.test(),
                                                 OneWireAddress.of("28.4BBB68080000"))));

        brewmaster.monitor(vesselId, Period.oneSecond());
    }
}
