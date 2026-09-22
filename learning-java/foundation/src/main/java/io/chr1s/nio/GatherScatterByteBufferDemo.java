package io.chr1s.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * 分散读取，聚合写入。问题：这么原理是什么？为什么要这么做？
 *
 * @author gongqi <qgong92@gmail.com>
 * Created on 2021-08-06
 */
public class GatherScatterByteBufferDemo {

    private static final String ROOT = "/Users/gongqi/Desktop/";

    public static void main(String[] args) throws IOException {
        File source = Paths.get(ROOT, "test.txt").toFile();
        FileInputStream is = new FileInputStream(source);
        FileChannel isChannel = is.getChannel();
        FileOutputStream os = new FileOutputStream(Paths.get(ROOT,"test4.txt").toFile());
        FileChannel osChannel = os.getChannel();

        ByteBuffer buffer1 = ByteBuffer.allocate(10);
        ByteBuffer buffer2 = ByteBuffer.allocate(10);
        ByteBuffer buffer3 = ByteBuffer.allocate(10);

        ByteBuffer[] buffers = new ByteBuffer[] {buffer1, buffer2, buffer3};

        long read;
        long totalLength = 0;
        while ((read = isChannel.read(buffers)) != -1) {
            totalLength += read;
            Arrays.stream(buffers)
                    .map(buffer -> "position=" + buffer.position() + ",limit=" + buffer.limit())
                    .forEach(System.out::println);
            // 切换模式
            Arrays.stream(buffers)
                    .forEach(Buffer::flip);
            // 聚合输出
            osChannel.write(buffers);
            // 清空缓冲区
            Arrays.stream(buffers)
                    .forEach(Buffer::clear);
        }
        System.out.println("总长度：" + totalLength);
        // 关闭资源
        is.close();
        isChannel.close();
        os.close();
        osChannel.close();
    }
}
