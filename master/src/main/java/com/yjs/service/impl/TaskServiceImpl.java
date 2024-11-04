package com.yjs.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.yjs.common.Result;
import com.yjs.factory.TaskFactory;
import com.yjs.model.NodeService;
import com.yjs.model.Task;
import com.yjs.model.TaskStatus;
import com.yjs.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname TaskServiceImpl
 * @Description
 * @Version 1.0.0
 * @Date 2024/03/29 19:58
 * @Created by Enzuo
 */
@Service
@Slf4j
public class TaskServiceImpl implements TaskService {
    Map<String, Task> map = new HashMap<>();
    Map<String, TaskFactory> factoryCache = new HashMap<>();
    final Lock lock = new ReentrantLock();
    Long outTime = 100000000L;
    @Autowired
    List<TaskFactory> taskFactoryList;
    int n = 0;

    @PostConstruct
    public void init() {
        log.info("创建任务");
        for (TaskFactory taskFactory : taskFactoryList) {
            List<Task> tasks = taskFactory.registerTask();
            for (Task task : tasks) {
                registerTask(task);
                factoryCache.put(task.getName(), taskFactory);
            }
        }
        log.info("创建任务完成");

    }

    @Override
    public Result getTask(NodeService nodeService) {
        synchronized (lock) {
            for (Task value : map.values()) {
                if (value.getStatus().equals(TaskStatus.READY)
                        || value.getStatus().equals(TaskStatus.FAIL)) {
                    log.info(nodeService + "获取任务" + value);
                    value.setNodeName(nodeService.getName());
                    value.setStatus(TaskStatus.RUNNING);
                    value.setTime(new Date());
                    value.setOutTime(new Date(new Date().getTime() + outTime));
                    return Result.ok().data("taskName", value.getName()).data("task", value).data("finish", false);
                }
            }
            return Result.ok().setMessage("任务已经都完成了").data("finish", true);
        }


    }

    @Override
    public Result getAllTask() {
        return Result.ok().data("tasks", map);
    }

    @Override
    public Result finishTask(Task task) {
        synchronized (lock) {
            Task task1 = map.get(task.getName());
            if (task1.getStatus().equals(TaskStatus.SUCCESS)) {
                return Result.ok().setMessage("不要重复提交");
            }
            task1.setStatus(TaskStatus.SUCCESS);
            task1.setResult(task.getResult());
            task1.setFinishTime(new Date());
            map.put(task.getName(), task1);
            log.info(task.getNodeName() + "完成任务" + task);
            return Result.ok();
        }


    }

    @Override
    public Result failTask(Task task) {
        synchronized (lock) {
            Task task1 = map.get(task.getName());
            task1.setStatus(TaskStatus.FAIL);
            task1.setResult(task.getResult());
            map.put(task.getName(), task1);
            log.info("任务失败" + task);
            lock.unlock();
            return Result.ok();
        }


    }

    @Override
    public void registerTask(Task task) {
        if (ObjectUtil.isEmpty(task.getName())) {
            task.setName(UUID.randomUUID().toString());
        }
        map.put(task.getName(), task);
    }

    @Override
    public void refreshTask() {
        synchronized (lock) {
            int n = 0;
            for (Task value : map.values()) {
                if (value.getStatus().equals(TaskStatus.READY)) {
                    continue;
                }
                if (!value.getStatus().equals(TaskStatus.SUCCESS)) {
                    if (new Date().getTime() - value.getTime().getTime() > value.getOutTime().getTime()) {
                        value.setStatus(TaskStatus.READY);
                    }
                } else {
                    if (!value.isPrint()) {
                        this.n++;
                        log.info("node:" + value.getNodeName() + "完成任务" + value.getName()
                                + "结果:" + value.getResult()
                                + "用时：" + (value.getFinishTime().getTime() - value.getTime().getTime())
                                + "目前完成数量为" + this.n + "/" + map.size()
                        );
                        TaskFactory taskFactory = factoryCache.get(value.getName());


                        taskFactory.finishOneTask(value);
                        value.setPrint(true);
                    }
                }

            }
            if (this.n == map.size()) {
                Map<TaskFactory, List<Task>> taskFactoryListHashMap = new HashMap<>();
                for (Task value : map.values()) {
                    TaskFactory taskFactory = factoryCache.get(value.getName());
                    List<Task> list = taskFactoryListHashMap.getOrDefault(taskFactory, new ArrayList<>());
                    list.add(value);
                    taskFactoryListHashMap.put(taskFactory, list);
                }
                taskFactoryListHashMap.forEach(TaskFactory::finishAllTask);
                log.info("全部完成");
                map.clear();
            }
        }


    }

    @Override
    public String getStatus() {
        return "目前完成数量为" + this.n + "/" + (Math.max(map.size(), this.n));
    }
}
