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

package me.nguba.gambrinus.raspberry;

import me.nguba.gambrinus.Pid;
import me.nguba.gambrinus.event.EventPublisher;
import me.nguba.gambrinus.event.ProcessValueChanged;
import me.nguba.gambrinus.event.SetpointChanged;

import com.google.common.eventbus.Subscribe;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Pi3
{
    public static Pi3 with(final EventPublisher publisher)
    {
        final Pi3 pi3 = new Pi3();
        publisher.subscribe(pi3);
        return pi3;
    }

    Pid controller = new Pid(0, 0, 0);

    private Pi3()
    {
        super();
    }

    @Subscribe
    public void onEvent(final ProcessValueChanged event)
    {
        // TODO fetch pin for vessel and calculate the output
        event.getVesselId();

        final double output = controller.getOutput(event.getProcessValue().getValue());
        if (output > 0) {
        }
    }

    @Subscribe
    public void onEvent(final SetpointChanged event)
    {
        controller.setSetpoint(event.getSetpoint().getValue());
    }
}
