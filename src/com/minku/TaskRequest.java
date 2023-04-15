package com.minku;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class TaskRequest {
    String taskId;
    List<String> dependentTaskIds = new ArrayList<>();
    Callable<Void> callable;

    public TaskRequest(String taskId, Callable<Void> callable) {
        this.taskId = taskId;
        this.callable = callable;
    }

    public TaskRequest(String taskId, List<String> dependentTaskIds, Callable<Void> callable) {
        this.taskId = taskId;
        this.dependentTaskIds = dependentTaskIds;
        this.callable = callable;
    }
}
