package io.chr1s.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2021-08-06
 */
public class FileTransferDemo {

    private static final String ROOT = "/Users/gongqi/Desktop";

    private void transformToTest() throws IOException {
        File source = Paths.get(ROOT, "test.txt").toFile();
        File target = Paths.get(ROOT, "test2.txt").toFile();

        FileInputStream is = new FileInputStream(source);

        FileChannel isChannel = is.getChannel();

        FileOutputStream os = new FileOutputStream(target);
        FileChannel osChannel = os.getChannel();

        //        ByteBuffer buffer = ByteBuffer.allocate(1024);

        isChannel.transferTo(0, source.length(), osChannel);

        is.close();
        os.close();
        isChannel.close();
        osChannel.close();
    }

    private void transformFormTest() throws IOException {
        File source = Paths.get(ROOT, "test.txt").toFile();
        File target = Paths.get(ROOT, "test3.txt").toFile();

        FileInputStream is = new FileInputStream(source);
        FileOutputStream os = new FileOutputStream(target);

        FileChannel isChannel = is.getChannel();
        FileChannel osChannel = os.getChannel();

        osChannel.transferFrom(isChannel, 0L, source.length());
    }

    public static void main(String[] args) throws Exception {
        FileTransferDemo demo = new FileTransferDemo();
        demo.transformFormTest();
//        demo.transformToTest();
    }
}
