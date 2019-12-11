package com.campusactivity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.campusactivity.core")
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class CampusactivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusactivityApplication.class, args);
    }

}
