package io.chr1s.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author gongqi <qgong92@gmail.com>
 * Created on 2021-08-04
 */
public class ByteBufTest {

    public static void main(String[] args) {

        // 堆内存分配初始大小为128字节的缓冲区
        ByteBuf heapBuffer = Unpooled.buffer(128);
        // 堆外内存分配初始大小为128字节的缓冲区
        ByteBuf directBuffer = Unpooled.directBuffer(128);
        // 创建原始字节数组或缓冲的一个视图, 视图之间共享一份原始数据
        ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(new byte[128], new byte[256]);
        // 创建原始字节数组或缓冲的一个深度拷贝的副本，数据不共享
        ByteBuf copiedBuffer = Unpooled.copiedBuffer(new byte[128]);
    }
}
