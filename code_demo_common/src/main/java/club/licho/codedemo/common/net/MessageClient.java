package club.licho.codedemo.common.net;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import javax.swing.plaf.synth.SynthTextAreaUI;

import club.licho.codedemo.common.util.GeneratorRandomName;

/**
 * ClassName:MessageClient
 * 模拟客户端
 *
 * @author licho
 * @create 2018-04-16 9:08
 */
public class MessageClient implements Runnable {
    private Logger log= LoggerFactory.getLogger(MessageClient.class);
    private Selector selector;
    private SocketChannel socketChannel;
    private String clientName= GeneratorRandomName.getDefaultName("messageClient");//客户端名称
    private String host;//连接的远程服务器host
    private int port;//连接的远程服务器port
    private volatile boolean stop=false;

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    /**
     * @param clientPort 客户端bind port
     */
    public MessageClient(int clientPort){

        try{
            this.selector=Selector.open();
            SocketChannel channel = this.socketChannel = SocketChannel.open();
            channel.bind(new InetSocketAddress(clientPort));
            channel.configureBlocking(false);
        }catch (IOException e){
            log.info("客户端"+clientName+"启动失败",e);
        }
    }
    public void doWiriter(SocketChannel channel){
        //将数据写入通道，向服务器写入消息
        try {
            byte[] datas="我们不一样不一样".getBytes("UTF-8");
            ByteBuffer buffer=ByteBuffer.allocate(datas.length);
            buffer.put(datas);
            buffer.flip();
            channel.write(buffer);
            if(!buffer.hasRemaining()){
                //说明一次全部写入通道
                log.info("向服务器发送消息成功，消息内容{}",new String(datas,"UTF-8"));
            }else{
                //需要多次写入
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        SocketChannel channel=this.socketChannel;
        try {
            if(this.socketChannel.connect(new InetSocketAddress(host,port))){
                log.info("连接服务器"+host+":"+port+"成功");
                //如果直接连接成功，那么监听读取事件
                channel.register(selector,SelectionKey.OP_READ);
                //将数据写入通道，向服务器写入消息
                doWiriter(channel);
            }else{
                this.socketChannel.register(selector, SelectionKey.OP_CONNECT);
            }
        } catch (IOException e) {
            log.info("客户端和服务器建立连接失败",e);
        }
        while(!stop){
            try{
                selector.select(1000);
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    SocketChannel keyChannel=(SocketChannel) key.channel();
                    iterator.remove();
                    if(key.isConnectable()){
                        //如果直接连接成功，那么监听读取事件
                        if(keyChannel.finishConnect()){
                            keyChannel.register(selector,SelectionKey.OP_READ);
                            //写入消息
                            doWiriter(keyChannel);
                        }else{
                            log.debug("连接服务器失败,程序退出");
                            System.exit(0);
                        }
                    }else if(key.isReadable()){
                        readClientInfo(key.channel());
                    }
                }
            }catch (Exception e){
                log.debug("未知异常，客户端程序严重异常",e);
                System.exit(0);
            }

        }
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /**
     * client出去readable状态，可以进行读取了。
     * @param client
     */
    private void readClientInfo(SelectableChannel client){
        if(client instanceof SocketChannel){
            SocketChannel channel=(SocketChannel) client;
            ByteBuffer buffer=ByteBuffer.allocate(1024);
            try {
                int nums = channel.read(buffer);//将字节写入到到ByteBuffer。
                if(nums>0){
                    buffer.flip();//翻转buffer，表示将limit置为position，position置为0。
                    byte [] datas=new byte[buffer.remaining()];
                    buffer.get(datas);//将position-limit之间的数据复制到该数据
                    String body=new String(datas,"UTF-8");
                    log.info("收到服务器端消息:"+body+"消息是否完整："+body.equals("success"));
                }else if(nums<0){
                    //流结束或者已关闭
                    throw new RuntimeException();
                }else{
                    //字节数为0
                    ;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
