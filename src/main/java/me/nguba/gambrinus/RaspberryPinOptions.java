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

import org.springframework.boot.context.properties.ConfigurationProperties;

import me.nguba.gambrinus.raspberry.GPIO;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
@ConfigurationProperties(prefix = "gambrinus.raspberry.pin")
public class RaspberryPinOptions
{
    private GPIO bk;

    private GPIO hlt;

    private GPIO mt;

    public GPIO getBk()
    {
        return bk;
    }

    public GPIO getHlt()
    {
        return hlt;
    }

    public GPIO getMt()
    {
        return mt;
    }

    public void setBk(final GPIO bk)
    {
        this.bk = bk;
    }

    public void setHlt(final GPIO hlt)
    {
        this.hlt = hlt;
    }

    public void setMt(final GPIO mt)
    {
        this.mt = mt;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        builder.append("RaspberryPinOptions [mt=").append(mt).append(", hlt=").append(hlt)
                .append(", bk=").append(bk).append("]");
        return builder.toString();
    }
}
