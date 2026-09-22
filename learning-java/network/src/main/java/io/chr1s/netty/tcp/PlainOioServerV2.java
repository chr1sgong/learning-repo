package io.chr1s.netty.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2021-08-04
 */
public class PlainOioServerV2 {

    private void serve(int port) throws IOException {
        final ServerSocket server = new ServerSocket(port);
        for (;;) {
            try {
                final Socket client = server.accept();
                new Thread(() -> {
                    try {
                        InetAddress clientAddr = client.getInetAddress();
                        OutputStream os = client.getOutputStream();
                        String msg = "Hi, friends from " + clientAddr.getHostAddress();
                        os.write(msg.getBytes());
                        os.flush();
                    } catch (IOException e) {
                        System.out.println("Oops.");
                        e.printStackTrace();
                    } finally {
                        try {
                            client.close();
                        } catch (IOException e) {
                            System.out.println("Oooooooooooops");
                        }
                    }

                });
            } catch (IOException ex) {
                System.out.println("Oops. server error");
            } finally {
                server.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        PlainOioServerV2 oioServer = new PlainOioServerV2();
        oioServer.serve(8090);
    }
}
