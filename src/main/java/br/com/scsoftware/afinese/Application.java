package br.com.scsoftware.afinese;

import br.com.scsoftware.afinese.infrastructure.config.Timezones;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.scsoftware.*")
@EntityScan(basePackages = "br.com.scsoftware.*")
@EnableJpaRepositories(basePackages = "br.com.scsoftware")
@EnableCaching
public class Application {

    public static void main(String[] args) {
        Timezones.initialize();
        Timezones.setTimeZoneFromUf("PR");
        SpringApplication.run(Application.class, args);
    }

}
