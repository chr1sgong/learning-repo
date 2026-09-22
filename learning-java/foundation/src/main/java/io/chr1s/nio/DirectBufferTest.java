package io.chr1s.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

/**
 * 问题：对于一个700M的文件，缓存大小为1024字节时，使用直接缓冲区读写文件的耗时要比使用堆缓冲区慢很多？
 * 而当缓冲区大小设置为5 * 1024 * 1024 （5M）时，使用直接缓冲区读写文件的耗时要比使用堆缓冲区快
 * @author gongqi <qgong92@gmail.com>
 * Created on 2021-08-06
 */
public class DirectBufferTest {

    private static final String ROOT = "/Users/gongqi/Downloads/";

    private static final String TARGET_ROOT = "/Users/gongqi/Desktop/";

    public static void main(String[] args) throws IOException {

        long curr = System.currentTimeMillis();

        File source = Paths.get(ROOT, "ideaIU-2020.1.4.dmg").toFile();
        FileInputStream is = new FileInputStream(source);
        FileChannel isChannel = is.getChannel();

        FileOutputStream os = new FileOutputStream(Paths.get(TARGET_ROOT, "ideaIU-2020.1.4.dmg").toFile());
        FileChannel osChannel = os.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(5 * 1024 * 1024);
//        ByteBuffer buffer = ByteBuffer.allocate(5 * 1024 * 1024);
        long read;
        while ((read = isChannel.read(buffer)) != -1) {
            buffer.flip();
            osChannel.write(buffer);
            buffer.clear();
        }
        is.close();
        isChannel.close();
        os.close();
        osChannel.close();

        System.out.println("time cost: " + (System.currentTimeMillis() - curr));
    }
}
