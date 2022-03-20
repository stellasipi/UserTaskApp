package hu.tbs.usertaskapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UserTaskAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserTaskAppApplication.class, args);
    }

}
