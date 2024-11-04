package com.yjs.config;

import com.yjs.model.NodeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

/**
 * @Classname ServiceConfig
 * @Description
 * @Version 1.0.0
 * @Date 2024/04/09 10:00
 * @Created by Enzuo
 */
@Configuration
public class ServiceConfig {
    @Value("${master.url:http://127.0.0.1:8080}")
    String hostname;

    @Bean
    public NodeService nodeService() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        String ip = inetAddress.getHostAddress();
        NodeService nodeService = new NodeService();
        nodeService.setName(UUID.randomUUID().toString());
        nodeService.setUrl(ip + ":" + 7070);
        nodeService.setIsWork(false);
        return nodeService;
    }
}
