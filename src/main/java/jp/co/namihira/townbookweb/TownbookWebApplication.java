package jp.co.namihira.townbookweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TownbookWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TownbookWebApplication.class, args);
    }

}
