package com.yjs.scheduled;

import com.yjs.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Classname ScheduledTask
 * @Description
 * @Version 1.0.0
 * @Date 2024/03/29 20:32
 * @Created by Enzuo
 */
@Component
public class ScheduledTask {
    @Autowired
    TaskService taskService;
    @Scheduled(fixedDelay = 500)
    public void task(){
        taskService.refreshTask();
    }

}
