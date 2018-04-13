package club.licho.codedemo.common.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;

/**
 * 这是服务器收到消息后的一个处理策略
 * ClassName:DiscardServerHandler
 *
 * @author licho
 * @create 2018-04-12 23:44
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 当通道接收到消息时就会触发该方法。
     * @param ctx
     * @param msg   是一个ByteBuf对象,叫做引用计数器，处理器的职责就是一定要处理该类对象，通过它的release()方法进行释放
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
            //处理消息

            ByteBuf message=(ByteBuf) msg;
//            byte[] datas=new byte[1024];
//            while(message.isReadable()){
//                ByteBuf byteBuf = message.readBytes(datas);
//
//            }
            ctx.write(msg);
            ctx.flush();
        }finally {
            //一定要释放该类对象
//            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
