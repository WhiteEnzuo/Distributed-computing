package com.yjs.task;

import com.yjs.model.Task;
import com.yjs.model.TaskStatus;
import com.yjs.service.TaskRunService;
import com.yjs.utls.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Classname TaskRunServiceIMpl
 * @Description
 * @Version 1.0.0
 * @Date 2024/04/09 10:53
 * @Created by Enzuo
 */
@Service
@Slf4j
public class TaskRunServiceImpl implements TaskRunService {
    @Override
    public Task runTask(Task task) {
        log.info("接收任务"+task);
        Map<String, Object> map = task.getMap();
        Long num = JsonUtils.toInstance(map.get("num"), Long.class);
        double pi = getPI(num);
        Map<String, Object> result = new HashMap<>();
        result.put("result", pi);
        task.setResult(result);
        task.setStatus(TaskStatus.SUCCESS);
        log.info("完成任务"+task);
        return task;
    }

    public double getPI(long totalPoints) {
        // 使用 Random 类生成随机数
        Random random = new Random();
        long insideCircle = 0;
        // 在正方形区域内生成随机点，并判断是否在圆内
        for (int i = 0; i < totalPoints; i++) {
            double x = random.nextDouble(); // 在 [0, 1) 范围内生成 x 坐标
            double y = random.nextDouble(); // 在 [0, 1) 范围内生成 y 坐标
            double distance = Math.sqrt(x * x + y * y); // 计算点到原点的距离
            if (distance <= 1) { // 判断点是否在单位圆内
                insideCircle++;
            }
        }

        // 计算 π 的近似值
        return 4.0 * insideCircle / totalPoints;
    }
}
