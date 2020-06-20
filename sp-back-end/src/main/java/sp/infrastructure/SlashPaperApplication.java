package sp.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

// @org.springframework.boot.autoconfigure.SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"sp.application", "sp.infrastructure", "sp.interfaces"})
public class SlashPaperApplication {

  public static void main(String[] args) {
    SpringApplication.run(SlashPaperApplication.class, args);
  }
}
