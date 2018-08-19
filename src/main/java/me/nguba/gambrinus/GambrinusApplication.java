package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.command.EventPublisher;
import me.nguba.gambrinus.equipment.VesselRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GambrinusApplication
{
    public static void main(final String... args)
    {
        SpringApplication.run(GambrinusApplication.class, args);
    }

    @Bean
    public VesselRepository vesselRepository()
    {
        return new VesselRepository();
    }

    @Bean
    public EventPublisher eventPublisher()
    {
        return new DefaultEventPublisher();
    }

    @Bean
    public MashCommandProcessorFactory mashCommandProcessor(final EventPublisher publisher,
                                                            final VesselRepository vessels)
    {
        return new MashCommandProcessorFactory(publisher, vessels);
    }

    @Bean
    public MashQueryProcessorFactory mashQueryProcessor(final VesselRepository vessels)
    {
        return new MashQueryProcessorFactory(vessels);
    }

    @Bean
    public Brewmaster brewmaster(final MashCommandProcessorFactory commands,
                                 final MashQueryProcessorFactory queries)
    {
        return new Brewmaster(commands, queries);
    }

    @Bean
    public AdminQueryProcessorFactory adminQueryProcessor(final VesselRepository vessels)
    {
        return new AdminQueryProcessorFactory(vessels);
    }

    @Bean
    public AdminCommandProcessorFactory adminCommandProcessor(final EventPublisher publisher)
    {
        return new AdminCommandProcessorFactory(publisher);
    }

    @Bean
    public Administrator administrator(final AdminCommandProcessorFactory commands,
                                       final AdminQueryProcessorFactory queries)
    {
        return new Administrator(commands, queries);
    }
}
