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
package me.nguba.gambrinus.brew;

import me.nguba.gambrinus.Brewmaster;
import me.nguba.gambrinus.Period;
import me.nguba.gambrinus.ddd.validation.ValidationFailed;
import me.nguba.gambrinus.equipment.VesselId;
import me.nguba.gambrinus.process.Temperature;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
@RestController
@RequestMapping(path = "/api/brew")
public final class BrewController
{
    private final Brewmaster brewmaster;

    private BrewController(final Brewmaster brewmaster)
    {
        this.brewmaster = brewmaster;
    }

    @PutMapping("/heat/{vesselId}/{temperature}")
    public void heat(@PathVariable("vesselId") final String vesselId,
                     @PathVariable("temperature") final double temperature)
            throws ValidationFailed
    {
        brewmaster.heat(VesselId.of(vesselId), Temperature.celsius(temperature));
    }

    @GetMapping("/temperature/{vesselId}")
    public Temperature readTemperature(@PathVariable("vesselId") final String vesselId)
            throws ValidationFailed
    {
        return brewmaster.readProcessValue(VesselId.of(vesselId));
    }

    @PutMapping("/monitor/{vesselId}")
    public void start(@PathVariable("vesselId") final String vesselId,
                      @RequestParam(defaultValue = "10", name = "period",
                              required = false) final String period)
            throws ValidationFailed
    {
        brewmaster.monitor(VesselId.of(vesselId), Period.from(period));
    }
}
