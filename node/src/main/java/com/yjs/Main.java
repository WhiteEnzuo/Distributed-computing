package com.yjs;

import com.yjs.service.TaskClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Classname Main
 * @Description
 * @Version 1.0.0
 * @Date 2024/03/29 19:16
 * @Created by Enzuo
 */
@SpringBootApplication
@EnableScheduling
@Slf4j
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);
        TaskClientService bean = run.getBean(TaskClientService.class);
        log.info("启动任务");
        bean.runTask(args);
    }
}
