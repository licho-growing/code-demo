package club.licho.codedemo.common.bean;

import com.alibaba.fastjson.JSON;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * ClassName:RocketMq的同步消费
 *
 * @author licho
 * @create 2018-05-21 14:53
 */
public class SysncConsumer {
    private static final Logger log= LoggerFactory.getLogger(SysncConsumer.class);
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("DefaultConsumer");
        consumer.setNamesrvAddr("192.168.138.128:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        consumer.subscribe("DefaultTopic", "TagA");

        consumer.registerMessageListener(new MessageListenerConcurrently() {

            AtomicLong consumeTimes = new AtomicLong(0);

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println("接收到消息msgs:{" +JSON.toJSONString(msgs)+"}");
                log.info("接收到消息msgs:{}" ,JSON.toJSONString(msgs));
                List<ConsumeConcurrentlyStatus> resutls = msgs.stream().map(each -> {
                    try {
                        System.out.println("消息内容为:"+ new String(each.getBody(), "UTF-8"));
                        log.info("消息内容为：" + new String(each.getBody(), "UTF-8"));
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        System.out.println("消息解码失败");
                        log.info("消息解码失败", e);
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                }).collect(Collectors.toList());
                return resutls.get(0);
            }

        });
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }

}
