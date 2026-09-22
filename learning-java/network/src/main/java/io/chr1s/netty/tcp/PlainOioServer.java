package io.chr1s.netty.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2021-06-24
 */
public class PlainOioServer {

    // 1. 创建serverSocket
    // 2. 绑定端口
    // 3. 监听
    public void serve(int port) throws IOException {
        // 等价于以下
        // final ServerSocket socket = new ServerSocket();
        // socket.bind(new InetSocketAddress(port));
        // 这里有个backlog的概念，该参数通常被描述为用来限制进来的连接队列长度
        final ServerSocket socket = new ServerSocket(port); // 将服务器绑定到指定端口
        for (;;) {
            final Socket clientSocket = socket.accept();    // 接受连接, 这一步会阻塞
            System.out.println("Accept Connection from " + clientSocket);
            // 创建一个线程来处理连接业务
            new Thread(() -> {
                OutputStream os;
                try {
                    os = clientSocket.getOutputStream();
                    os.write("Hi!\r\n".getBytes(StandardCharsets.UTF_8));
                    os.flush();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    // 释放资源
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        // do nothing
                    }
                }
            }).start();
        }
    }

    public static void main(String[] args) throws IOException {
        new PlainOioServer().serve(9527);
    }
}
