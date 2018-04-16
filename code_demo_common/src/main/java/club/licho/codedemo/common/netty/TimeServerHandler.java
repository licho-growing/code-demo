package club.licho.codedemo.common.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * ClassName:实现一个处理器，在服务器启动后收到请求连接时主动返回一个时间，然后就中断连接。
 *
 * @author licho
 * @create 2018-04-14 0:47
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 当和客户端建立稳定连接时，该方法会被调用，我们在这里直接向客户端写入一个时间然后就关闭连接
     * @param ctx
     */
    @Override
    public void channelActive(final ChannelHandlerContext ctx) { // (1)
        final ByteBuf time = ctx.alloc().buffer(4); // (2)申请四个字节
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                ctx.close();
            }
        }); // (4)
    }

    /**
     * 当发生异常时关闭通道处理上下文
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}