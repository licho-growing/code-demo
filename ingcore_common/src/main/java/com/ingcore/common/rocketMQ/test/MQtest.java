package com.ingcore.common.rocketMQ.test;

import com.ingcore.common.message.MessageHandlers;

/**
 * ClassName:MQtest
 *
 * @author yangli
 * @create 2017-12-18 18:26
 */
public class MQtest implements MessageHandlers{
    @Override
    public void handle(String message) {
        System.out.println("MQtest==========="+message);
    }


    public static void main(String[] args) {

//        String mqNameServer = "192.168.0.224:9876";
//        String mqTopics = "MQ-MSG-TOPICS-TEST";
//
//        String consumerMqGroupName = "CONSUMER-MQ-GROUP";
//
//        MQtest mQtest = new MQtest();
//
//        MessageConsumer rkMQConsumer = new MessageConsumer();
//        rkMQConsumer.startMqConsumer(mqNameServer,mqTopics,consumerMqGroupName,mQtest);
    }
}
