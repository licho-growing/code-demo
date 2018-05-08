package club.licho.codedemo.common.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * ClassName:MessageServer
 *消息服务器，对于接受到的请求都返回succes
 * @author licho
 * @create 2018-04-15 23:38
 */
public class MessageServer implements Runnable{
    private Logger log= LoggerFactory.getLogger(MessageServer.class);
    private Selector selector;//选择器,通过遍历活跃的channel，如果有准备好的读写事件，就会更新状态
    private ServerSocketChannel serverSocketChannel;//服务器channel
    private volatile  boolean stop=false;//关闭线程的标志
    public MessageServer(int port){
        try{
            this.selector=Selector.open();
            this.serverSocketChannel=ServerSocketChannel.open();//开启一个服务器channel
            this.serverSocketChannel.configureBlocking(false);//配置为分阻塞的
            this.serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);//backlog是设备同时最大连接数
            this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//为serverChannel注册accpet监听事件
        }catch (IOException e){
            log.info("MessageServer创建失败",e);
        }
    }
    @Override
    public void run() {
        while(!stop){
            //顺序轮询Selector中已经准备好了的accept,read,writer事件。对客户的请求进行处理。
            try{
                selector.select(1000);
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    try{
                        handlerSelectorKey(key);
                    }catch (Exception e){
                        log.debug("处理SelectorKey失败",e);
                        if(key!=null){
                            key.cancel();//发生异常这次处理就失败了，取消该次事件
                            if(key.channel()!=null){
                                key.channel().close();
                            }
                        }
                    }
                }
            }catch (Throwable e){
                log.error("发生严重程序错误，服务器可能已崩溃",e);
                try{
                    selector.close();
                    serverSocketChannel.close();
                }catch (Exception a){
                    e.printStackTrace();
                }

                System.exit(1);
            }
        }
    }

    private void handlerSelectorKey(SelectionKey key){
        if(!key.isValid()){
            return;
        }
        if(key.isAcceptable()){
            //说明有了新的客户端请求。
          acceptNewConnection(key.channel());
        }else if(key.isReadable()){
            //连接成功后，且已经读取到客户端发送的所有消息
            readClientInfo(key.channel());
        }else if(key.isWritable()){

        }
    }

    private void acceptNewConnection(SelectableChannel channel){
        if(channel instanceof ServerSocketChannel){
            ServerSocketChannel server=(ServerSocketChannel)channel;
            try {
                SocketChannel accept = server.accept();
                //为客户端注册监听事件。
                accept.configureBlocking(false);
                accept.register(selector,SelectionKey.OP_READ);//监听其可读事件。
            } catch (IOException e) {
               log.debug("accept request fail",e);
            }
        }else{
            log.debug("无法处理的accept事件:options{}",channel.validOps());
        }
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
                    log.info("收到客户端消息:"+body+"消息是否完整："+body.equals("我们不一样不一样"));
                    //接收到消息后向客户端端进行响应
                    ByteBuffer writer=ByteBuffer.allocate(1024);
                    writer.put("success".getBytes("UTF-8"));
                    writer.flip();
                    channel.write(writer);//将position至limit的数据写入
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
