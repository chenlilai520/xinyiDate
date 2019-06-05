package com.example.esdemotest.thread;

import com.example.esdemotest.utils.JedisPoolUtils;
import redis.clients.jedis.Jedis;

/**
 * @author chenlilai
 * @title: MessageProducer
 * @projectName xinyiDate
 * @description:
 * @date 2019/6/411:36
 */
public class MessageProducer extends Thread {
//    public static final String MESSAGE_KEY = "message:queue";
    public static final String MESSAGE_KEY = "channel:1";
    private  int count;

    public void putMessage(String message) {
        Jedis jedis = JedisPoolUtils.getJedis();
        Long size = jedis.publish(MESSAGE_KEY, message);
        System.out.println(size);
        System.out.println(Thread.currentThread().getName() + " put message,size=" + size + ",count=" + count);
        count++;
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < 5; i++) {
            putMessage("message" + count);
        }
    }

    public static void main(String[] args) {
        MessageProducer messageProducer = new MessageProducer();
        Thread t1 = new Thread(messageProducer, "thread1");
        Thread t2 = new Thread(messageProducer, "thread2");
        Thread t3 = new Thread(messageProducer, "thread3");
        Thread t4 = new Thread(messageProducer, "thread4");
        Thread t5 = new Thread(messageProducer, "thread5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}