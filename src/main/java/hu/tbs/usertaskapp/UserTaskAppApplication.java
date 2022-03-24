package hu.tbs.usertaskapp;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@SpringBootApplication
@EnableScheduling
public class UserTaskAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserTaskAppApplication.class, args);
    }

    @Autowired
    public UserTaskAppApplication(DataSource dataSource) {
        Flyway.configure().baselineOnMigrate(true).dataSource(dataSource).load().migrate();
    }

}
