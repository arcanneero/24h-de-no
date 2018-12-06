package net.arcann.telethonno.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"net.arcann.telethonno"})
public class EngineApplication {

    public static void main(final String[] args) {
        SpringApplication.run(EngineApplication.class, args);
    }



}