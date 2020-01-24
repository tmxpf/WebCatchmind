package com.springboot.catchmind;

import com.springboot.catchmind.controller.ChatController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CatchmindApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatchmindApplication.class, args);
    }

}
