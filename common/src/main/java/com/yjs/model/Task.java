package com.yjs.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @Classname Task
 * @Description
 * @Version 1.0.0
 * @Date 2024/03/29 19:30
 * @Created by Enzuo
 */
@Data
public class Task {
    private String name;
    private String nodeName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date outTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date finishTime;
    private String status;
    private boolean isPrint;
    private Map<String, Object> result;
    private Map<String, Object> map;
}
