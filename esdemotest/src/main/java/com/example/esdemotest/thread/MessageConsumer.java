package com.example.esdemotest.thread;

import com.example.esdemotest.utils.JedisPoolUtils;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;

/**
 * @author chenlilai
 * @title: MessageConsumer
 * @projectName xinyiDate
 * @description:
 * @date 2019/6/411:38
 */
public class MessageConsumer implements Runnable {
    public static final String MESSAGE_KEY = "message:queue";
    private volatile int count;

    public static final String EXIT_COMMAND = "exit";//结束程序的消息

    private MyJedisPubSub myJedisPubSub = new MyJedisPubSub();//处理接收消息

    public static final String CHANNEL_KEY = "channel:1";//频道

    public void consumerMessage() {
        Jedis jedis = JedisPoolUtils.getJedis();
        jedis.subscribe(myJedisPubSub,CHANNEL_KEY);
    }

    @Override
    public void run() {
        while (true) {
            consumerMessage();
        }
    }

    public static void main(String[] args) {
        MessageConsumer messageConsumer = new MessageConsumer();
        Thread t1 = new Thread(messageConsumer, "thread6");
        Thread t2 = new Thread(messageConsumer, "thread7");
        t1.start();
        t2.start();
    }
}