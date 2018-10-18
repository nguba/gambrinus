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

package me.nguba.gambrinus.equipment;

import me.nguba.gambrinus.ddd.Entity;

/**
 * Equipment to increase or decrease heat in a container.
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public abstract class HeatExchanger extends Entity<HeatExchangerId> implements Switched
{
    protected HeatExchanger(final HeatExchangerId id)
    {
        super(id);
    }

}
