package io.chr1s.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2021-08-12
 */
public class GroupChatClient {

    private Selector selector;
    private SocketChannel socketChannel;
    private String userName;

    public GroupChatClient() {
        try {
            this.socketChannel = SocketChannel.open(new InetSocketAddress(9999));  // open and connect
            this.socketChannel.configureBlocking(false);
            this.selector = Selector.open();
            this.socketChannel.register(selector, SelectionKey.OP_READ);
            this.userName = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println(userName + " is ok-.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void sendMsg(String msg) {
        msg = userName + "说：" + msg;
        try {
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void readMsg() {
        try {
            int cnt = selector.select();
            if (cnt > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        channel.read(byteBuffer);
                        System.out.println(new String(byteBuffer.array()));
                    }
                    iterator.remove();;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GroupChatClient client = new GroupChatClient();
        new Thread(() -> {
            while (true) {
                client.readMsg();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String msg = scanner.nextLine();
            client.sendMsg(msg);
        }
    }

}
