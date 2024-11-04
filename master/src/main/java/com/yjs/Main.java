package com.yjs;

import com.yjs.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @Classname Main
 * @Description
 * @Version 1.0.0
 * @Date 2024/03/29 19:15
 * @Created by Enzuo
 */
@SpringBootApplication
@EnableScheduling
public class Main  {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);
    }
}
