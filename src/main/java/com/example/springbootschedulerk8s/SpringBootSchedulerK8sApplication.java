package com.example.springbootschedulerk8s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringBootSchedulerK8sApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSchedulerK8sApplication.class, args);
    }

}
