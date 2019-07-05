package com.camel.bpm.utils;

import com.camel.core.entity.process.Comment;
import com.camel.core.entity.process.UserTask;
import org.activiti.engine.task.Task;

import java.util.ArrayList;
import java.util.List;

public class ActivitiObj2SystemObjUtils {
    private final static ActivitiObj2SystemObjUtils INSTANCE = new ActivitiObj2SystemObjUtils();

    public ActivitiObj2SystemObjUtils() {
    }

    public static ActivitiObj2SystemObjUtils getInstance(){return INSTANCE;}

    public List<Comment> commentsToObj(List<org.activiti.engine.task.Comment> comments) {
        List<Comment> result = new ArrayList<>();
        comments.forEach(comment -> {
            Comment c1 = new Comment(comment.getUserId(), comment.getTime(), comment.getTaskId(), comment.getFullMessage());
            result.add(c1);
        });
        return result;
    }

    public List<UserTask> tasks2UserTasks(List<Task> tasks) {
        List<UserTask> userTaskList = new ArrayList<>();
        tasks.forEach(task -> {
            UserTask userTask = new UserTask();
            userTask.setId(task.getId());
            userTask.setProcessInstanceId(task.getProcessInstanceId());
            userTask.setUsername(task.getAssignee());
            userTask.setEnd(false);
            userTask.setName(task.getName());
            userTask.setProcessDefinitionId(task.getProcessDefinitionId());
            userTaskList.add(userTask);
        });
        return userTaskList;
    }
}
