package com.yjs.cache;

import cn.hutool.cache.Cache;
import cn.hutool.cache.impl.LRUCache;
import com.yjs.model.NodeService;
import com.yjs.model.Task;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Classname ServiceCache
 * @Description
 * @Version 1.0.0
 * @Date 2024/03/29 19:23
 * @Created by Enzuo
 */
@Data
@Component
@Slf4j
public class ServiceCache {
    Cache<String, NodeService> cache = new LRUCache<>(10);

    public NodeService getService(String serviceName) {
        return cache.get(serviceName);
    }

    public void putService(NodeService nodeService) {
        cache.put(nodeService.getName(), nodeService, 1000);
    }
    public List<NodeService> getServiceList() {

        List<NodeService> list = new ArrayList<>();
        for (NodeService service : cache) {
            list.add(service);
        }
        return list;
    }

}
