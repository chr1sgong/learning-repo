package io.chr1s.netty.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2021-08-06
 */
public class PlainNioClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("172.29.79.3", 19527));

        if (!socketChannel.isConnected()) {
            while (!socketChannel.finishConnect()) {
                System.out.println("尝试连接服务器...");
            }
        }

        String msg = "Hello, NIO Server!";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append(msg);
        }
        socketChannel.write(ByteBuffer.wrap(sb.toString().getBytes()));
        System.out.println("xxxxxxxxxxxxxxxxxxxx");
        // 先挂在这里
        System.in.read();
    }
}
