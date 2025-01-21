package com.tiki.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TikiServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TikiServerApplication.class, args);
    }

}
