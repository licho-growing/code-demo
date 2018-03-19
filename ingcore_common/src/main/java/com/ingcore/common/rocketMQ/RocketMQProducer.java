package com.ingcore.common.rocketMQ;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
/**
 *
 * RocketMQ生产端
 *
 * ClassName:RocketMQProducer
 *
 * @author yangli
 * @create 2017-12-14 18:31
 */
public class RocketMQProducer {

    private static final Logger logger = LoggerFactory.getLogger(RocketMQProducer.class);

    private static DefaultMQProducer sender;

    private static final Integer lock = 1;

    // TODO 目前共用一个groupname可以使用单个DefaultMQProducer，
    // TODO 后期如果是多个的话要考虑多个DefaultMQProducer
    public static DefaultMQProducer getProducer(String nameServer, String producerGroupName) {
        if(sender == null) {
            synchronized (lock) {
                sender = new DefaultMQProducer(producerGroupName);
                sender.setNamesrvAddr(nameServer);
                sender.setInstanceName(UUID.randomUUID().toString());
                try {
                    sender.start();
                    return sender;
                } catch (MQClientException e) {
                    logger.error("启动mq失败", e);
                    throw new RuntimeException("启动mq失败", e);
                }
            }
        } else {
            return sender;
        }
    }

    public void send(Message message) {

        try {
            SendResult result = sender.send(message);
            SendStatus status = result.getSendStatus();
            logger.info("RocketMQProducer ==> messageTopic="+message.getTopic()+", messageId=" + result.getMsgId() + ", status=" + status);
        } catch (Exception e) {
            logger.error("发送消息失败" + JSON.toJSONString(message), e);
        }
    }
}
