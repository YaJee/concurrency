package com.mmall.concurrency.example.immutable;


import com.mmall.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ThreadSafe
public class ImmutableExample2 {

    private  static Map<Integer, Integer> map = new HashMap<>();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        // 不可以修改
        map.put(1, 3);
        System.out.println("{}"+map.get(1));
    }

}
