package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

//启动类注解
@SpringBootApplication
@Slf4j
public class BlilibliliApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(BlilibliliApp.class, args);
    }
}
