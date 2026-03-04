package com.interview.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockProblem01 {
    // Red Flag: HashMap is NOT thread-safe for concurrent writes
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
                // Try to acquire the lock to update the cache
                if (lock.tryLock()) {
                    try {
                        // LIVELOCK: The "Politeness" check
                        if (otherWorker.isBusy()) {
                            System.out.println(name + ": Other worker is busy. I'll wait.");

                            // Threads are ACTIVE (looping), not BLOCKED.
                            // This consumes CPU cycles unlike a Deadlock.
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

        // Link them so they can see each other's "Busy" state
        workerA.setOtherWorker(workerB);
        workerB.setOtherWorker(workerA);

        new Thread(workerA).start();
        new Thread(workerB).start();
    }

}
