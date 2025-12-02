package de.schmiereck.liquidVote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"de.schmiereck.liquidVote"})
public class LiquidVoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiquidVoteApplication.class, args);
    }
}

