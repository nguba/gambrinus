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

package me.nguba.gambrinus.scheduler.state;

import me.nguba.gambrinus.scheduler.SchedulerContext;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public enum Ramp implements State
{
    INSTANCE;

    @Override
    public void handle(final SchedulerContext ctx)
    {
        if (ctx.hasSetpointReached())
            ctx.setState(Soak.INSTANCE);
    }

    @Override
    public String toString()
    {
        return "RAMP";
    }
}
