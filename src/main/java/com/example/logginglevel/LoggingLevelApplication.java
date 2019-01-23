package com.example.logginglevel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LoggingLevelApplication {

    private static Logger logger = LoggerFactory.getLogger(LoggingLevelApplication.class);


    public static void main(String[] args) {
        logger.debug("Logging at debug!!");
        logger.error("Logging at error!!");
        SpringApplication.run(LoggingLevelApplication.class, args);
    }

    @GetMapping(path = "/")
    public String doStuff(@RequestHeader("X-Logging-Level") String loggingLevelHeader) {

        logger.debug("Debug Level");
        logger.info("Info Level");
        logger.warn("Warn Level");
        logger.error("Error Level");
        return "Done";
    }



}

