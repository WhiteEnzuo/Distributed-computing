package com.yjs.utils;

import com.yjs.common.Result;
import com.yjs.model.NodeService;
import com.yjs.model.Task;
import com.yjs.utls.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Classname HttpUtils
 * @Description
 * @Version 1.0.0
 * @Date 2024/04/09 09:29
 * @Created by Enzuo
 */
@Component
@Slf4j
public class HttpUtils {
    @Value("${master.url:http://127.0.0.1:8080}")
    String hostname;


    public Result register(NodeService nodeService) {
        String url = hostname + "/master/service/v1/register";
        return httpRequest(url, nodeService);
    }

    public Result getTask(NodeService nodeService) {
        String url = hostname + "/master/service/v1/getTask";
        return httpRequest(url, nodeService);
    }


    public Result finishTask(Task task) {
        String url = hostname + "/master/service/v1/finishTask";
        return httpRequest(url, task);
    }

    public Result failTask(Task task) {
        String url = hostname + "/master/service/v1/failTask";
        return httpRequest(url, task);
    }

    private Result httpRequest(String url, Object object) {
        // 创建 RestTemplate 实例
        RestTemplate restTemplate = new RestTemplate();

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 将对象转换为 JSON 字符串
        String requestBody = JsonUtils.toJson(object);

        // 创建请求实体
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // 发送 POST 请求
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class);

        // 获取响应体
        String body = responseEntity.getBody();
        log.info(body);
        Result result = JsonUtils.toInstance(body, Result.class);
        return result;
    }
}
