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

import me.nguba.gambrinus.GambrinusOptions;
import me.nguba.gambrinus.RaspberryPinOptions;
import me.nguba.gambrinus.WebMvcUtil;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.onewire.OneWireAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 *
 */
@RestController
@RequestMapping(path = "/api/admin")
public class AdminController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    private final AdminResourceService admin;

    private final GambrinusOptions options;

    private AdminController(final AdminResourceService admin,
                            final GambrinusOptions options,
                            final RaspberryPinOptions pins)
    {
        this.admin = admin;
        this.options = options;

        LOGGER.info("{}", pins);
    }

    @PostMapping(path = "vessel/{id}/{sensor}")
    public Object createVessel(@PathVariable("id") final VesselId id,
                               @PathVariable("sensor") final OneWireAddress address,
                               final UriComponentsBuilder builder)
            throws Exception
    {
        admin.createVessel(id, address, options.getMountpoint());

        return WebMvcUtil.created(builder.path("/vessel/{id}/{sensor}"), id, address);
    }

    @GetMapping(path = "sensor")
    public Object getSensors() throws Exception
    {
        return admin.findAddresses(options.getMountpoint());
    }

    @GetMapping(path = "vessel/{name}")
    public Object getVessel(@PathVariable("name") final String name) throws Exception
    {
        return admin.findVessel(VesselId.of(name));
    }

    @GetMapping(path = "vessel")
    public Object getVessels() throws Exception
    {
        return admin.findVessels();
    }
}
