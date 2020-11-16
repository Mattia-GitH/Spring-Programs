package com.SpringH2DB.SpringH2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SpringH2Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringH2Application.class, args);
    }
}
