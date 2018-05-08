package club.licho.codedemo.common.netty.handler;

import com.alibaba.fastjson.JSON;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

import javax.swing.plaf.synth.SynthTextAreaUI;

import club.licho.codedemo.common.netty.bean.TransMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 读取到客户端发送的完整消息，然后返回当前服务器的时间
 *
 * @author licho
 * @create 2018-04-30 11:21
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger log= LoggerFactory.getLogger(TimeServerHandler.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String finalMsg=(String)msg;
        try{
            if(StringUtils.isNotBlank(finalMsg)){
                TransMessage transMessage = JSON.parseObject(finalMsg, TransMessage.class);//获取到客户端的请求。
                log.info("收到来自设备"+transMessage.getDeviceId()+"的消息，其设备名称为:"+transMessage.getDeviceName()+",消息为:"+transMessage);
                //调用DeviceService进行处理。
            }
        }catch (Exception e){
            //发生异常,记录异常消息记录。
        }
        String currentTime=Calendar.getInstance().toString()+ System.getProperty("line.separator");
        ByteBuf byteBuf = Unpooled.copiedBuffer(currentTime.getBytes());
        ChannelFuture f = ctx.writeAndFlush(byteBuf);
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                assert f==channelFuture;
                ctx.close();//响应成功后关闭连接
            }
        });
    }

//    /**
//     * 读取完成刷新ctx，将消息队列中的消息写入到SocketChannel中发送给对方。
//     * @param ctx
//     * @throws Exception
//     */
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
