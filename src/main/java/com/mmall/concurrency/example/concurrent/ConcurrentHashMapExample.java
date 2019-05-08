package com.mmall.concurrency.example.concurrent;

import com.mmall.concurrency.annotations.ThreadSafe;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
@ThreadSafe
public class ConcurrentHashMapExample {

    //
    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    private static Map<Integer,Integer> map = new ConcurrentSkipListMap<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for(int i = 0; i < clientTotal; i++){
            final int count = i;
            executorService.execute(()->{

                try {
                    semaphore.acquire();
                    updaate(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("size:"+map.size());
    }
    private static void updaate(int i){
        map.put(i,i);
    }
}
