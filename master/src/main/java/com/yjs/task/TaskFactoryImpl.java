package com.yjs.task;

import com.yjs.factory.TaskFactory;
import com.yjs.model.Task;
import com.yjs.model.TaskStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Classname TaskFactoryImpl
 * @Description
 * @Version 1.0.0
 * @Date 2024/04/09 10:44
 * @Created by Enzuo
 */
@Component
@Slf4j
public class TaskFactoryImpl implements TaskFactory {
    private long sum = 10000000000L;
    private Date createTime = new Date();

    @Override
    public List<Task> registerTask() {
        List<Task> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            long taskNum = sum / 100;
            Task task = new Task();
            task.setStatus(TaskStatus.READY);
            Map<String, Object> map = new HashMap<>();
            map.put("num", taskNum);
            task.setMap(map);
            list.add(task);
        }
        return list;
    }

    @Override
    public void finishOneTask(Task task) {

    }

    @Override
    public void finishAllTask(List<Task> tasks) {
        double sumPi = 0;
        for (Task task : tasks) {
            Map<String, Object> result = task.getResult();
            sumPi += (double) result.get("result");
        }
        log.info("最终值为" + sumPi / 100);
        System.out.println("总用时"+(new Date().getTime() - createTime.getTime()));
//        System.exit(0);
    }
}
