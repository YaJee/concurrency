package com.mmall.concurrency.example.syncContainer;

import com.google.common.collect.Lists;
import com.mmall.concurrency.annotations.ThreadSafe;

import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@ThreadSafe
public class VectorExample1 {


    public static int clientTotal = 5000;
    public static int threadTotal = 200;

    private static List<Integer> list = new Vector<>();


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService =  Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for(int i=0; i<clientTotal; i++){
            final int count = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("size:"+list.size());
    }

    private static void update(int i){
        list.add(i);
    }
}
