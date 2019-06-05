package com.example.mq;

import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author chenlilai
 * @title: Producer
 * @projectName xinyiDate
 * @description:
 * @date 2019/5/2020:10
 */
public class Producer extends  EndPoint {


    public Producer(String endpointName) throws IOException {
        super(endpointName);
    }

    public void sendMessage(Serializable object) throws IOException
    {
        channel.basicPublish("", endPointName, null, SerializationUtils.serialize(object));
    }
}
