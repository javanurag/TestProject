package com.minku;

public class TaskStatus {
    private boolean isComplete;

    public synchronized void complete (){
        isComplete=true;
        notifyAll();
    }

    public synchronized boolean isComplete() throws InterruptedException {
        if(!isComplete)
            wait();
        return true;
    }
}
