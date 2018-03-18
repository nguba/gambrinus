package me.nguba.gambrinus;

import me.nguba.gambrinus.domain.hardware.TemperatureService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author <a href="mailto:nguba@mac.com">Nico Guba</a>
 */
@SpringBootApplication
public class GambrinusApplication implements CommandLineRunner {

  public static void main(final String[] args) {
    SpringApplication.run(GambrinusApplication.class, args);
  }

  @Override
  public void run(final String... args) throws Exception {
  }

  @Bean
  public TemperatureService bus() {
    return new TemperatureService();
  }
}
