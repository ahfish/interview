package com.interview.pr;

import java.util.concurrent.*;

public class Test02 {
    private static final ExecutorService pool = Executors.newFixedThreadPool(20);

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            final int j = i;
            pool.submit(() -> processA("Task-" + j));
        }

    }

    private static void processA(String name) {
        System.out.println(name + " [Parent] started. Waiting for Process B...");

        Future<String> futureB = pool.submit(() -> processB(name));

        try {
            String result = futureB.get();
            System.out.println(name + " [Parent] finished with: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String processB(String name) {
        System.out.println(name + " [Child] is running!");
        return "Success from B";
    }
}