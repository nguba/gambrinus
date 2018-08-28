package me.nguba.gambrinus;

import me.nguba.gambrinus.admin.AdminService;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.event.EventPublisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({ GambrinusOptions.class, RaspberryPinOptions.class })
public class GambrinusApplication
{
    public static void main(final String... args)
    {
        SpringApplication.run(GambrinusApplication.class, args);
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

    @Bean
    public EventPublisher eventPublisher()
    {
        return new GuavaEventPublisher();
    }

    @Bean
    public MashQueries mashQueries(final VesselRepository vessels)
    {
        return new MashQueries(vessels);
    }

    @Bean
    public MashCommands mashCommands(final VesselRepository vessels, final EventPublisher events)
    {
        return new MashCommands(vessels, events);
    }

    @Bean
    public Brewmaster brewmaster(final MashCommands commands,
                                 final MashQueries queries)
    {
        return new Brewmaster(commands, queries);
    }

    @Bean
    public AdminQueries adminQueries(final VesselRepository vessels)
    {
        return new AdminQueries(vessels);
    }

    @Bean
    public AdminCommands adminCommands(final VesselRepository vessels)
    {
        return new AdminCommands(vessels);
    }

    @Bean
    public Administrator administrator(final AdminCommands commands,
                                       final AdminQueries queries)
    {
        return new Administrator(commands, queries);
    }

    @Bean
    public AdminService adminService(final Administrator admin)
    {
        return new AdminService(admin);
    }
}
