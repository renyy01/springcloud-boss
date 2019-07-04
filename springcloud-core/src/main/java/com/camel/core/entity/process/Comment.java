package com.camel.core.entity.process;

import lombok.Data;

import java.util.Date;

/**
 @author baily
 */
@Data
public class Comment {
    private String id;

    private String userId;

    private Date time;

    private String taskId;

    private String processInstanceId;

    private String type;

    private String fullMessage;

    private String username;

    public Comment() {
    }

    public Comment(String userId, Date time, String taskId, String fullMessage) {
        this.userId = userId;
        this.time = time;
        this.taskId = taskId;
        this.fullMessage = fullMessage;
    }
}
