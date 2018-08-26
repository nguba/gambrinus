/**
 * 
 */
package me.nguba.gambrinus;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import me.nguba.gambrinus.eventstore.EventStore;

@SpringBootApplication
public class InfrastructureContext
{
  public static void main(String[] args)
  {
    SpringApplication.run(InfrastructureContext.class, args);
  }

  @Bean
  public EventStore eventStore(DataSource ds) {
    return EventStore.with(ds);
  }
}
