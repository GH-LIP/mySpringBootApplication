package com.test.io;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOTest {

    @Test
    public void test () throws IOException {
        // Java NIO read and write operation
        // nio read
        FileInputStream fileInputStream = new FileInputStream("F:\\编程资料\\新文档.txt");
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        channel.read(byteBuffer);
        // nio write
        StringBuffer sb = new StringBuffer();

    }

}
