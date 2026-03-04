package com.interview.pr;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test01 {

    private static final Map<String, String> sharedCache = new HashMap<>();
    private static final Lock lock = new ReentrantLock();

    static class Worker implements Runnable {
        private final String name;
        private boolean busy = true; // Internal state
        private Worker otherWorker;

        public Worker(String name) {
            this.name = name;
        }

        public void setOtherWorker(Worker other) {
            this.otherWorker = other;
        }

        public boolean isBusy() {
            return this.busy;
        }

        @Override
        public void run() {
            while (busy) {
                if (lock.tryLock()) {
                    try {
                        if (otherWorker.isBusy()) {
                            System.out.println(name + ": Other worker is busy. I'll wait.");
                            Thread.sleep(50);
                            continue;
                        }

                        // Critical Section
                        sharedCache.put(name, "Updated at " + System.currentTimeMillis());
                        this.busy = false; // Task complete
                        System.out.println(name + ": Task Finished!");

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Worker workerA = new Worker("Service-A");
        Worker workerB = new Worker("Service-B");

        workerA.setOtherWorker(workerB);
        workerB.setOtherWorker(workerA);

        new Thread(workerA).start();
        new Thread(workerB).start();
    }
}