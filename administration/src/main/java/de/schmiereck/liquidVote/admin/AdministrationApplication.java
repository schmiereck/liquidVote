package de.schmiereck.liquidVote.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "de.schmiereck.liquidVote")
@EntityScan(basePackages = "de.schmiereck.liquidVote.shared")
@EnableJpaRepositories(basePackages = "de.schmiereck.liquidVote.shared")
public class AdministrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdministrationApplication.class, args);
    }
}

