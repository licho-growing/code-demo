package club.licho.codedemo.common.net;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * ClassName:MessageTestDemo
 *
 * @author licho
 * @create 2018-04-16 10:13
 */
public class MessageTestDemo {
    public static void main(String[] args) {
        MessageServer server=new MessageServer(7000);
        new Thread(server).start();
        Selector selector=null;
        try {
            selector=Selector.open();
        } catch (IOException e) {
            System.out.println("严重错误");
            System.exit(0);
        }
        for (int i = 0; i < 10; i++) {
            MessageClient client=new MessageClient(8000+i);
            client.setHost("localhost");
            client.setPort(7000);
            new Thread(client,"客户端线程"+i).start();
        }
    }
}
