package io.chr1s.netty.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;


/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2021-06-24
 */
public class PlainNioServer {

    public void serve(int port) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // 设置成非阻塞
        serverChannel.configureBlocking(false);
        // 等价于 ServerSocket sssocket = new ServerSocket(port);
//        ServerSocket ssocket = new ServerSocket();
//        InetSocketAddress address = new InetSocketAddress(port);
//        ssocket.bind(address);  // 将服务器绑定到指定端口
        serverChannel.bind(new InetSocketAddress(port));
        Selector selector = Selector.open();    // 打开Selector来处理channel
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);       // 将serverChannel注册到channel，以接受连接事件
        final ByteBuffer msg = ByteBuffer.wrap("Hi!\r\n".getBytes(StandardCharsets.UTF_8));

        for (;;) {
            try {
                selector.select(3000);  // 等待需要处理的新事件，阻塞一直持续到下一个传入事件
            } catch (IOException e) {
                e.printStackTrace();
                // handle exception
                break;
            }

            Set<SelectionKey> readyKeys = selector.selectedKeys();  // 获取所有接收事件的Selection-Key实例
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove(); // 从集合里面挪出来

                try {
                    // accept
                    if (key.isAcceptable()) {   // 检查事件是否是一个新的已经就绪可以被接受的连接
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        // 接受客户端，并将它注册到选择器
                        client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, msg.duplicate());
                        System.out.println("Accept Connection from " + client);
                    }

                    // 可读
                    if (key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        client.read(buffer);
                        System.out.println("receiving: " + new String(buffer.array()));
                    }

                    // 可写
                    if (key.isWritable()) { // 检查套接字是否已经准备好写内容了
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        while (buffer.hasRemaining()) {
                            if (client.write(buffer) == 0) {    // 将数据写到已连接的客户端
                                break;
                            }
                        }
//                        client.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    key.channel();
                    try {
                        key.channel().close();
                    } catch (IOException ex) {
                        // ignore on exception
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new PlainNioServer().serve(8999);
    }
}
