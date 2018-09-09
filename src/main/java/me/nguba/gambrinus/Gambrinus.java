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

import me.nguba.gambrinus.admin.AdminResourceService;
import me.nguba.gambrinus.brew.BrewQueries;
import me.nguba.gambrinus.brew.Brewmaster;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.event.EventPublisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ InfrastructureContext.class })
@EnableConfigurationProperties({ GambrinusOptions.class, RaspberryPinOptions.class })
public class Gambrinus
{
    public static void main(final String... args)
    {
        SpringApplication.run(Gambrinus.class, args);
    }

    @Bean
    public Administrator administrator(final VesselRepository repo)
    {
        return new Administrator(repo);
    }

    @Bean
    public AdminResourceService adminService(final Administrator admin)
    {
        return new AdminResourceService(admin);
    }

    @Bean
    public Brewmaster brewmaster(final BrewCommands commands,
                                 final BrewQueries queries)
    {
        return new Brewmaster(commands, queries);
    }

    @Bean
    public BrewCommands mashCommands(final VesselRepository vessels, final EventPublisher events)
    {
        return new BrewCommands(vessels, events);
    }

    @Bean
    public BrewQueries mashQueries(final VesselRepository vessels)
    {
        return new BrewQueries(vessels);
    }

    @Bean
    public PinConverter pinConverter()
    {
        return new PinConverter();
    }

    @Bean
    public VesselRepository vesselRepository()
    {
        return new VesselRepository();
    }
}
