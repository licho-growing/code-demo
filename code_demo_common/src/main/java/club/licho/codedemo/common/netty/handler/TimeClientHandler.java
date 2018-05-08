package club.licho.codedemo.common.netty.handler;

import com.alibaba.fastjson.JSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

import club.licho.codedemo.common.netty.bean.AriDevice;
import club.licho.codedemo.common.netty.bean.TransMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Handler线程安全，对于客户端的每个连接都会使用一个新的Handler对象来进行处理
 *
 * @author licho
 * @create 2018-04-30 11:48
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    private static final Logger log= LoggerFactory.getLogger(TimeClientHandler.class);
    private final String str="hi,server";
    private int counter=0;
    /**
     * 通道建立完毕主动向服务器发送消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 20; i++) {
            TransMessage transMessage = new TransMessage();
            transMessage.setAccessToken("123456");
            transMessage.setMsgType("connect");
            transMessage.setMsgBody(AriDevice.getDefaultVal());
            String str = JSON.toJSONString(transMessage)+System.getProperty("line.separator");
            ByteBuf byteBuf = Unpooled.copiedBuffer(str.getBytes("UTF-8"));
            //正常来说服务器应该收到20条消息，但是可能发生粘包拆包，导致多条消息合并了。合并成了两条，在网络条件极差的情况下
            //可能会拆分成更多条，例如100条
            ctx.writeAndFlush(byteBuf);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf message = (ByteBuf) msg;
//        byte[] buffer = new byte[message.readableBytes()];//判断ByteBuf中有多少个字节可读
//        message.readBytes(buffer);//将缓冲区中的数据读取到byte数组中
//        String finalMsg=new String(buffer,"UTF-8");

        String body=(String) msg;
        log.info("收到服务器"+ctx.channel().toString()+"的第"+(++counter)+"消息:"+body);
    }

    /**
     * 数据读取完毕时关闭通道
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("200-响应成功,成功关闭连接");
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
