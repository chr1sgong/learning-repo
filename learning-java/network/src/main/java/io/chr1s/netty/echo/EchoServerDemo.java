package io.chr1s.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;

import java.net.InetSocketAddress;

/**
 * @author gongqi
 * @since 2019-09-04
 */
public class EchoServerDemo {

    private final int port;

    public EchoServerDemo(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            System.err.println("Usage: " + EchoServerDemo.class.getSimpleName() + " <port>");
            return;
        }

        int port = Integer.parseInt(args[0]);
        new EchoServerDemo(port).start();
    }

    public void start() throws InterruptedException {
        // 业务逻辑在这里写
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 配置服务器
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(this.port))
                    // 配置channel，将有关的入站消息通知给EchoServerHandler实例
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline()
                                    .addLast(new DelimiterBasedFrameDecoder(10, true, true, Unpooled.copiedBuffer(" ".getBytes())))
                                    .addLast(serverHandler);
                        }
                    });

            // 异步地绑定服务器端口
            // bind()方法返回ChannelFuture实例，绑定是异步的
            // 调用ChannelFuture#sync()的方法阻塞当前线程直到绑定完成，注意sync()方法会抛出中断异常
            ChannelFuture future = bootstrap.bind().sync();
            System.out.println(EchoServerDemo.class.getName() +
                    " started and listening for connections on " + future.channel().localAddress());
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
