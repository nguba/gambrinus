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
package me.nguba.gambrinus.event;

import me.nguba.gambrinus.InfrastructureTest;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.event.sources.EventSource;
import me.nguba.gambrinus.event.sources.ProcessValueChangedSource;
import me.nguba.gambrinus.process.Temperature;

import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@InfrastructureTest
class EventSourceMediatorTest
{
    @Autowired
    private EventStore eventStore;

    @Autowired
    private EventPublisher publisher;

    private <E extends EventSource> int countEvents(final Class<E> target)
    {
        return eventStore.find(target).size();
    }

    @Test
    void monitorsProcessValueChanged()
    {
        final int before = countEvents(ProcessValueChangedSource.class);

        publisher.publish(ProcessValueChanged.on(VesselId.of("teapot"), Temperature.celsius(100)));

        final int after = countEvents(ProcessValueChangedSource.class);

        assertThat(after).isGreaterThan(before);
    }
}
