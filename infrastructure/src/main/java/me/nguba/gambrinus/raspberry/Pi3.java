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

import me.nguba.gambrinus.PIDController;
import me.nguba.gambrinus.command.temperature.process.ProcessValueChanged;
import me.nguba.gambrinus.command.temperature.setpoint.SetpointChanged;
import me.nguba.gambrinus.event.EventPublisher;

import com.google.common.eventbus.Subscribe;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class Pi3
{
    PIDController controller = new PIDController(0, 0, 0);
    
    public static Pi3 with(final EventPublisher publisher)
    {
        final Pi3 pi3 = new Pi3();
        publisher.subscribe(pi3);
        return pi3;
    }

    private Pi3()
    {
        super();
    }

    @Subscribe
    public void onEvent(ProcessValueChanged event)
    {
        // TODO fetch pin for vessel and calculate the output
        event.getVesselId();
        
        double output = controller.getOutput(event.getProcessValue().getValue());
        if(output > 0) {   
        }
    }

    @Subscribe
    public void onEvent(SetpointChanged event)
    {
        controller.setSetpoint(event.getSetpoint().getValue());
    }
}
