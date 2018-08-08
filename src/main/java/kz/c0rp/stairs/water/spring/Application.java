package kz.c0rp.stairs.water.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "kz.c0rp")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
