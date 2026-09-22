package io.chr1s.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2021-08-09
 */
public class GroupChatNioServer {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public GroupChatNioServer() throws IOException {
        this.selector = Selector.open();
        this.serverSocketChannel = ServerSocketChannel.open();
        this.serverSocketChannel.bind(new InetSocketAddress(9999));
        this.serverSocketChannel.configureBlocking(false);
        // 将注册到selector上
        this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listen() throws IOException {
        while (true) {
            int cnt = selector.select(2000);
            if (cnt > 0) {
                Set<SelectionKey> readyKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = readyKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    // business logic
                    if (key.isAcceptable()) {
                        SocketChannel clientChannel = serverSocketChannel.accept();
                        clientChannel.configureBlocking(false);
//                        SocketChannel clientChannel = (SocketChannel) key.acc();
                        clientChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println(clientChannel.getRemoteAddress() + "上线了-");
                    }
                    if (key.isReadable()) {
                        readData(key);
                    }
                    iterator.remove();
                }
            } else {
                System.out.println("等待客户端。。。");
            }
        }
    }

    public void readData(SelectionKey key) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int count = socketChannel.read(byteBuffer);
            if (count > 0) {
                String msg = new String(byteBuffer.array());
                System.out.println("msg from client: " + msg);
                // 转发到其它客户端
                notifyAllClient(msg, socketChannel);
            }
        } catch (Exception e) {
            if (socketChannel != null) {
                try {
                    System.out.println(socketChannel.getRemoteAddress() + "离线了。。。");
                    key.cancel();
                    socketChannel.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public void notifyAllClient(String msg, SocketChannel socketChannel) throws IOException {
        System.out.println("服务器转发消息-");
        for (SelectionKey selectionKey : selector.keys()) {
            Channel channel = selectionKey.channel();
            if (channel instanceof SocketChannel && channel != socketChannel) {
                // 强制转换为SocketChannel类型
                SocketChannel clientChannel = (SocketChannel) channel;
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                clientChannel.write(byteBuffer);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        GroupChatNioServer chatNioServer = new GroupChatNioServer();
        chatNioServer.listen();
    }
}
