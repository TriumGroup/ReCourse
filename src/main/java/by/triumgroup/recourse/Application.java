package by.triumgroup.recourse;

import by.triumgroup.recourse.configuration.MainConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(MainConfiguration.class, args);
    }
}
