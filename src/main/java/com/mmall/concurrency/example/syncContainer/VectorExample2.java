package com.mmall.concurrency.example.syncContainer;

import com.mmall.concurrency.annotations.NotThreadSafe;

import java.util.Vector;

/**
 * 同步容器也不安全
 * 在同时进行查询、修改操作时就会报异常
 * 数组越界
 *
 */
@NotThreadSafe
public class VectorExample2 {
    private  static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while(true){
            for(int i=0; i<10; i++){
                vector.add(i);
            }

            Thread thread1 = new Thread(){
                public void run(){
                    for(int i=0; i<vector.size(); i++){
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread(){
                public void run(){
                    for(int i=0; i<vector.size(); i++){
                        vector.get(i);
                    }
                }

            };
            thread1.start();
            thread2.start();
            System.out.println(vector.size());

        }
    }
}
