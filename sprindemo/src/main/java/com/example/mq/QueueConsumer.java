package com.example.mq;

/**
 * @author chenlilai
 * @title: QueueConsumer
 * @projectName xinyiDate
 * @description:
 * @date 2019/5/2020:12
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public class QueueConsumer extends EndPoint implements Runnable, Consumer {

    public QueueConsumer(String endPointName) throws IOException
    {
        super(endPointName);
    }

    public void run()
    {
        try
        {
            // start consuming messages. Auto acknowledge messages.
            channel.basicConsume(endPointName, true, this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Called when consumer is registered.
     */
    public void handleConsumeOk(String consumerTag)
    {
        System.out.println("Consumer " + consumerTag + " registered");
    }

    /**
     * Called when new message is available.
     */
    public void handleDelivery(String consumerTag, Envelope env, BasicProperties props, byte[] body) throws IOException
    {
        Map map = (HashMap) SerializationUtils.deserialize(body);
        System.out.println("Message Number " + map.get("message number") + " received.");

    }

    public void handleCancel(String consumerTag)
    {
    }

    public void handleCancelOk(String consumerTag)
    {
    }

    public void handleRecoverOk(String consumerTag)
    {
    }

    public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1)
    {
    }
}