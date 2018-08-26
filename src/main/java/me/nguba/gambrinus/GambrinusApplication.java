package me.nguba.gambrinus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import me.nguba.gambrinus.admin.AdminResourceService;
import me.nguba.gambrinus.equipment.VesselRepository;
import me.nguba.gambrinus.event.EventPublisher;

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
  public BrewQueries mashQueries(final VesselRepository vessels)
  {
    return new BrewQueries(vessels);
  }

  @Bean
  public BrewCommands mashCommands(final VesselRepository vessels, final EventPublisher events)
  {
    return new BrewCommands(vessels, events);
  }

  @Bean
  public Brewmaster brewmaster(final BrewCommands commands,
                               final BrewQueries queries)
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
  public AdminResourceService adminService(final Administrator admin)
  {
    return new AdminResourceService(admin);
  }
}
