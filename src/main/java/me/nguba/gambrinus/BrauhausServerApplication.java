package me.nguba.gambrinus;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
@SpringBootApplication
public class BrauhausServerApplication implements CommandLineRunner {

  public static void main(final String[] args) {
    SpringApplication.run(BrauhausServerApplication.class, args);
  }

  @Override
  public void run(final String... args) throws Exception {

  }

  @Bean
  public Bus bus() {
    return new Bus();
  }
}
