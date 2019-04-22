package com.example.executo;

import org.junit.Test;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class MyRunnable  implements  Runnable{


        public MyRunnable(){

    }

        // 正在执行的任务数
        private int num;

        public MyRunnable(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            System.out.println("正在执行的MyRunnable " + num);
            try {
                Thread.currentThread().sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyRunnable " + num + "执行完毕");

        }

    /**
     * list Map拿取集合
      */
    public  void test(){
        List<Map<String,Object>> list=new ArrayList<>();
        for(int i=0;i<10;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("banthId",i);
            map.put("name",i);
            list.add(map);
        }
        List<BigInteger> ownerIds = list.stream().map(mm -> new BigInteger(mm.get("banthId").toString())).collect(Collectors.toList());

    }
}




