package com.mmall.concurrency.example.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample3 {


    private final  static int threadCount = 20;

    public static void main(String[] args) {

        ExecutorService exec  = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);

        for ( int i = 0; i < threadCount; i++){
            final int threadNum = i;
            exec.execute(()->{
                try {
                    if(semaphore.tryAcquire()){

                        test(threadNum);
                        semaphore.release();
                    }

                }catch (Exception e){
                    System.out.println("exception"+e);
                }

            });
        }
        exec.shutdown();
    }
    private static void test(int threadNum) throws InterruptedException {

        System.out.println(threadNum);
        Thread.sleep(1000);
    }
}
