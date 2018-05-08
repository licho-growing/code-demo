package club.licho.codedemo.common.netty;

import java.nio.charset.Charset;

import club.licho.codedemo.common.netty.handler.TimeClientHandler;
import club.licho.codedemo.common.netty.handler.TimeServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * ClassName:DisCardClient
 *
 * @author licho
 * @create 2018-04-30 11:42
 */
public class DisCardClient {

    public void connect(int port,String host)throws Exception{
        //配置客户端NIO线程组
        EventLoopGroup group=new NioEventLoopGroup();

        try {
            Bootstrap bootstrap=new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new InitHandler());
            //发起异步连接操作
            ChannelFuture sync = bootstrap.connect(host, port).sync();
            //等待客户端链路关闭
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅退出，释放NIO线程组
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        try {
            for (int i=0;i<1;i++){
                new Thread(()->{
                    try {
                        new DisCardClient().connect(8080,"localhost");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class InitHandler extends ChannelInitializer<SocketChannel>{
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024*1024))
                    .addLast(new StringDecoder(Charset.forName("UTF-8")))
                    .addLast(new TimeClientHandler());
        }
    }
}
