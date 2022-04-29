package com.example.springbootschedulerk8s.scheduler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;

@Component
public class SandboxScheduler {

    private static final Logger log = LoggerFactory.getLogger(SandboxScheduler.class);

    @Scheduled(fixedRate = 10000)
    public void sayHello() throws IOException {
        log.info("hiya");
        var tempCsv = Files.readString(new FileSystemResource("./csv/temp.csv").getFile().toPath());
        log.info("Csv: \n{}", tempCsv);
    }

}
