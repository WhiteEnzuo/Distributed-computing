package com.yjs.service.impl;

import com.yjs.common.Result;
import com.yjs.model.NodeService;
import com.yjs.model.Task;
import com.yjs.model.TaskStatus;
import com.yjs.service.TaskClientService;
import com.yjs.service.TaskRunService;
import com.yjs.utils.HttpUtils;
import com.yjs.utls.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Classname TaskClientServiceImpl
 * @Description
 * @Version 1.0.0
 * @Date 2024/04/09 09:56
 * @Created by Enzuo
 */
@Service
@Slf4j
public class TaskClientServiceImpl implements TaskClientService {
    @Autowired
    HttpUtils httpUtils;
    @Autowired
    NodeService nodeService;
    @Autowired
    TaskRunService taskRunService;

    @Override
    public void runTask(String[] args) {
        while (true) {
            try {
                runTask();
                nodeService.setIsWork(false);
                Thread.sleep(100);
            }catch (Exception e){
                log.error(e.getMessage());
            }

        }
    }

    @Override
    public void registerService() {
        httpUtils.register(nodeService);
    }

    private void runTask() {
        nodeService.setIsWork(true);
        Result task = httpUtils.getTask(nodeService);
        Map<String, Object> data = task.getData();
        if (data.containsKey("finish")) {
            if (!(boolean) data.get("finish")) {
                Object o = data.get("task");
                Task taskR = JsonUtils.toInstance(o, Task.class);
                nodeService.setTaskName(taskR.getName());

                taskR = taskRunService.runTask(taskR);
                nodeService.setTaskName("");
                if (taskR.getStatus().equals(TaskStatus.FAIL)) {
                    httpUtils.failTask(taskR);
                }
                if (taskR.getStatus().equals(TaskStatus.SUCCESS)) {
                    httpUtils.finishTask(taskR);
                }
            }

        }
    }

}
