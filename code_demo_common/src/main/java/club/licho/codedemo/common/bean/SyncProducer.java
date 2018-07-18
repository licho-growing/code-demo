package club.licho.codedemo.common.bean;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * RocketMQ的同步消息发送。
 * ClassName:SyncProducer
 *
 * @author licho
 * @create 2018-04-12 10:54
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer("DefaultProducer");
        producer.setNamesrvAddr("192.168.138.128:9876");
        //Launch the instance.
        try {
            producer.start();
        } catch (MQClientException e) {
            System.out.println(e.getErrorMessage());
            System.out.print(e.getResponseCode());
            e.printStackTrace();
        }
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("DefaultTopic" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
