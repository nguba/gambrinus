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
package me.nguba.gambrinus.eventstore;

import me.nguba.gambrinus.InfrastructureTest;
import me.nguba.gambrinus.command.temperature.setpoint.SetpointChanged;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.owfs.ProcessValueChanged;
import me.nguba.gambrinus.process.Temperature;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;

@InfrastructureTest
class EventStoreTest
{
    @Autowired
    private EventStore eventStore;

    @Test
    void canStoreSetpointChanged() throws Exception
    {
        final SetpointChanged event = SetpointChanged.on(VesselId.of("mash"),
                                                         Temperature.celsius(72.0));
        eventStore.record(SetpointChangedSource.from(event));

        for (final SetpointChangedSource message : eventStore.find(SetpointChangedSource.class)) {
            System.out.println(message);
        }
    }

    @Test
    void canStoreProcessValueChanged() throws Exception
    {
        eventStore.record(ProcessValueChangedSource
                .from(ProcessValueChanged.on(VesselId.of("HLT"), Temperature.celsius(98.3))));

        for (final ProcessValueChangedSource source : eventStore
                .find(ProcessValueChangedSource.class)) {
            System.out.println(source);
        }
    }

}
