package com.mmall.concurrency.example.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample1 {

    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++){
            final int threadNum = i;
            Thread.sleep(1000);
            executor.execute(()->{
                try{
                    race(threadNum);
                }catch (Exception e){
                    System.out.println(e);
                }
            });
        }
        executor.shutdown();
    }
    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        System.out.println("i m ready" + threadNum);
        barrier.await();
        System.out.println("continue "+ threadNum);
    }
}
