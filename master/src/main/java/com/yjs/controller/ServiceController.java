package com.yjs.controller;

import com.yjs.cache.ServiceCache;
import com.yjs.common.Result;
import com.yjs.model.NodeService;
import com.yjs.model.Task;
import com.yjs.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname ServiceController
 * @Description
 * @Version 1.0.0
 * @Date 2024/03/29 19:50
 * @Created by Enzuo
 */
@RestController
@RequestMapping("/master/service")
public class ServiceController {
    @Autowired
    ServiceCache serviceCache;
    @Autowired
    TaskService taskService;

    @PostMapping("/v1/register")
    public Result register(@RequestBody NodeService nodeService) {
        serviceCache.putService(nodeService);
        return Result.ok();
    }


    @GetMapping("/v1/getService")
    public Result getService() {
        return Result.ok().data("service", serviceCache.getServiceList()).data("status",taskService.getStatus());
    }

    @GetMapping("/v1/getAllTask")
    public Result getAllTask() {
        return taskService.getAllTask();
    }



    @PostMapping("/v1/getTask")
    public Result getTask(@RequestBody NodeService nodeService) {
        return taskService.getTask(nodeService);
    }

    @PostMapping("/v1/finishTask")
    public Result finishTask(@RequestBody Task task) {

        return taskService.finishTask(task);
    }

    @PostMapping("/v1/failTask")
    public Result failTask(@RequestBody Task task) {
        return taskService.failTask(task);
    }
}
