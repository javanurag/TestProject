package com.test.taskservice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskServiceImpl implements TaskService {
    ExecutorService service;
    Map<String, TaskStatus> taskMap;

    public TaskServiceImpl() {
        service = Executors.newFixedThreadPool(10);
        taskMap = new ConcurrentHashMap<>();
    }

    @Override
    public void addTask(TaskRequest taskRequest) {
        service.execute(() -> {
            taskRequest.dependentTaskIds.forEach(taskId -> {
                TaskStatus taskStatus = taskMap.computeIfAbsent(taskId, tId -> new TaskStatus());
                try {
                    taskStatus.isComplete();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
            });


            try {
                taskRequest.callable.call();
                TaskStatus taskStatus = taskMap.computeIfAbsent(taskRequest.taskId, tid -> new TaskStatus());
                taskStatus.complete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
