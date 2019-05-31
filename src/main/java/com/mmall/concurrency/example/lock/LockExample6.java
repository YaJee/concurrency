package com.mmall.concurrency.example.lock;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample6 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                reentrantLock.lock();
                System.out.println("wait signal");//1

                condition.await();      //释放锁，进入condition的等待队列
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("get signal");//4

            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            System.out.println("get lock");//2

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();     //让位于condition等待队列的线程去竞争锁

            System.out.println("send signal ~ ");//3
            reentrantLock.unlock();
        }).start();
    }
}
