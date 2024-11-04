package com.yjs.model;

import lombok.Data;

/**
 * @Classname NodeService
 * @Description
 * @Version 1.0.0
 * @Date 2024/03/29 19:24
 * @Created by Enzuo
 */
@Data
public class NodeService {
    private String name;
    private String url;
    private Boolean isWork;
    private String taskName;
}
