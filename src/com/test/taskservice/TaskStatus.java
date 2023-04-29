package com.test.taskservice;

public class TaskStatus {
    private boolean isComplete;

    public synchronized void complete (){
        isComplete=true;
        notifyAll();
    }

    public synchronized boolean isComplete() throws InterruptedException {
        while(!isComplete)
            wait();
        return true;
    }
}
