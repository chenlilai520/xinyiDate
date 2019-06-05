package com.example.esdemotest.thread;

/**
 * @author chenlilai
 * @title: MyJedisPubSub
 * @projectName xinyiDate
 * @description:
 * @date 2019/6/511:26
 */

import redis.clients.jedis.JedisPubSub;

/**
 * 继承JedisPubSub，重写接收消息的方法
 */
class MyJedisPubSub extends JedisPubSub {
    @Override
    /** JedisPubSub类是一个没有抽象方法的抽象类,里面方法都是一些空实现
     * 所以可以选择需要的方法覆盖,这儿使用的是SUBSCRIBE指令，所以覆盖了onMessage
     * 如果使用PSUBSCRIBE指令，则覆盖onPMessage方法
     * 当然也可以选择BinaryJedisPubSub,同样是抽象类，但方法参数为byte[]
     **/
    public void onMessage(String channel, String message) {
        System.out.println(Thread.currentThread().getName()+"-接收到消息:channel=" + channel + ",message=" + message);
        //接收到exit消息后退出
        if (MessageConsumer.EXIT_COMMAND.equals(message)) {
            System.exit(0);
        }
    }
}