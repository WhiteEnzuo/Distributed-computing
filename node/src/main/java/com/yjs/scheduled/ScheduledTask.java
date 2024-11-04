package com.yjs.scheduled;

import com.yjs.service.TaskClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    @Autowired
    TaskClientService taskClientService;
    @Scheduled(fixedDelay = 500)
    public void task(){
        taskClientService.registerService();
    }

}