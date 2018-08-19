package me.nguba.gambrinus;

import me.nguba.gambrinus.cqrs.command.CommandProcessor;
import me.nguba.gambrinus.cqrs.command.EventPublisher;
import me.nguba.gambrinus.cqrs.query.QueryProcessor;
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
    public CommandProcessor commandProcessor(final EventPublisher publisher,
                                             final VesselRepository vessels)
    {
        return new MashCommandProcessorFactory(publisher, vessels).make();
    }

    @Bean
    public QueryProcessor queryProcessor(final VesselRepository vessels)
    {
        return new MashQueryProcessorFactory(vessels).make();
    }

    @Bean
    public Brewmaster brewmaster(final CommandProcessor commands, final QueryProcessor queries)
    {
        return new Brewmaster(commands, queries);
    }
}
