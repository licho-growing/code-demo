package com.ingcore.common.message;

import com.ingcore.common.rocketMQ.RocketMQProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName:MessageProducer
 *
 * @author yangli
 * @create 2017-12-18 17:20
 */
public class MessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);

//    private String mqNameServer = "192.168.0.224:9876";

    /**
     * 发送MQ消息
     */
    public static void sendMq(String mqNameServer, String producerGroupName, Message message){
        try {
            RocketMQProducer.getProducer(mqNameServer, producerGroupName).send(message);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            logger.error(e.getMessage(), e);
            System.exit(0);
        }
    }
}
