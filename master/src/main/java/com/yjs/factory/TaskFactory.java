package com.yjs.factory;

import com.yjs.model.Task;

import java.util.List;

/**
 * @Classname TaskNode
 * @Description
 * @Version 1.0.0
 * @Date 2024/03/31 11:38
 * @Created by Enzuo
 */

public interface TaskFactory {
    public List<Task> registerTask();

    public void finishOneTask(Task task);

    public void finishAllTask(List<Task> tasks);
}
