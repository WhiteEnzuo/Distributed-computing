package com.yjs.service;

import com.yjs.common.Result;
import com.yjs.model.NodeService;
import com.yjs.model.Task;

/**
 * @Classname TaskService
 * @Description
 * @Version 1.0.0
 * @Date 2024/03/29 19:58
 * @Created by Enzuo
 */
public interface TaskService {
    public Result getTask(NodeService nodeService);
    public Result getAllTask();

    public Result failTask(Task task);

    public Result finishTask(Task task);

    public void registerTask(Task task);

    public void refreshTask();

    String getStatus();
}
