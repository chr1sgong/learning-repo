package io.chr1s.nio;

import java.nio.ByteBuffer;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2021-08-19
 */
public class ByteBufferTest {

    public static void main(String[] args) {
        ByteBuffer heapBuffer = ByteBuffer.allocate(100);
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(100);
    }
}
