package ru.hogwarts.schoolhogvarts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.hogwarts.schoolhogvarts.service.impl.StudentServiceImpl;

@SpringBootApplication
public class    SchoolApplication {
    private final static Logger logger = LoggerFactory.getLogger(SchoolApplication.class);

    public static void main(String[] args) {
        logger.info("Application SchoolApplication is starting!");
        SpringApplication.run(SchoolApplication.class, args);
    }

}
