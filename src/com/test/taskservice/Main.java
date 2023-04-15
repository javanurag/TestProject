package com.test.taskservice;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TaskRequest t2 = new TaskRequest("T2", Arrays.asList("T1"), () -> {
            System.out.println(" Completed T2 ");
            return null;
        });

        TaskService taskService  = new TaskServiceImpl();
        taskService.addTask(t2);

        Thread.sleep(600000);
        TaskRequest t1 = new TaskRequest("T1",() -> {
            System.out.println(" Completed T1 ");
            return null;
        });
        taskService.addTask(t1);


    }
}