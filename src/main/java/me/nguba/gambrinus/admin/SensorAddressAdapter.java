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
package me.nguba.gambrinus.admin;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import me.nguba.gambrinus.onewire.OneWireAddress;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
public final class SensorAddressAdapter
        extends ResourceAssemblerSupport<OneWireAddress, ResourceSupport>
{
    public static ResourceSupport adapt(final OneWireAddress entity)
    {
        return new SensorAddressAdapter().toResource(entity);
    }

    private SensorAddressAdapter()
    {
        super(AdminController.class, ResourceSupport.class);
    }

    @Override
    public ResourceSupport toResource(final OneWireAddress entity)
    {
        final ResourceSupport resource = new ResourceSupport();
        resource.add(ControllerLinkBuilder.linkTo(AdminController.class).slash("sensor")
                .slash(entity.getValue()).withSelfRel().withTitle("Maxim DS18B20"));
        return resource;
    }

}
