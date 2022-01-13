package br.com.scsoftware.afinese;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
    )
@ComponentScan(basePackages = "br.com.scsoftware.*")
@EntityScan(basePackages = "br.com.scsoftware.*")
@EnableJpaRepositories(basePackages = "br.com.scsoftware")
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
